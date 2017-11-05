package io.github.mcasper3.prep.recipes.camera

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.speech.tts.TextToSpeech
import android.support.design.widget.Snackbar
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.base.PrepActivity
import io.github.mcasper3.prep.data.api.FailureUiModel
import io.github.mcasper3.prep.data.api.InProgressUiModel
import io.github.mcasper3.prep.data.api.ocr.OcrResponse
import io.github.mcasper3.prep.util.PermissionHelper
import io.github.mcasper3.prep.util.extensions.resize
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotterknife.bindView
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CameraActivity : PrepActivity<CameraPresenter, CameraView>(), CameraView {

    @Inject override lateinit var presenter: CameraPresenter
    @Inject lateinit var permissionHelper: PermissionHelper

    private val takePhotoButton: Button by bindView(R.id.take_photo)
    private val choosePhotoButton: Button by bindView(R.id.choose_photo)
    private val loadingView: View by bindView(R.id.loading)
    private val rootView: View by bindView(R.id.topLayout)
    private val previewImage: ImageView by bindView(R.id.image_preview)

    private var disposables = CompositeDisposable()
    private var currentImagePath: String? = null
    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val listener = TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                Log.d("OnInitListener", "Text to speech engine started successfully.")
                tts?.language = Locale.US
            } else {
                Log.d("OnInitListener", "Error starting the text to speech engine.")
            }
        }
        tts = TextToSpeech(this.applicationContext, listener)

        takePhotoButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

        choosePhotoButton.setOnClickListener {
            if (permissionHelper.hasPermission(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    REQUEST_READ_EXTERNAL, R.string.read_external_rationale
            )) {

                dispatchChoosePictureIntent()
            }
        }
    }

    override fun onDestroy() {
        disposables.dispose()

        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (currentImagePath != null) {
            outState.putString("path", currentImagePath)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        currentImagePath = savedInstanceState?.getString("path")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && (requestCode == REQUEST_TAKE_PHOTO || requestCode == REQUEST_CHOOSE_PHOTO)) {
            processImage()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_READ_EXTERNAL) {
            if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                dispatchChoosePictureIntent()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun showParseResults(result: OcrResponse) {
        hideLoading()
    }

    override fun showLoading() {
        loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingView.visibility = View.GONE
    }

    override fun showEmpty() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(uiModel: FailureUiModel) {
        super.showError(uiModel)
        if (uiModel.hasErrorMessage()) {
            Snackbar.make(
                    rootView,
                    uiModel.errorMessage ?: getString(R.string.generic_error),
                    Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun dispatchChoosePictureIntent() {
        val choosePictureIntent = Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        choosePictureIntent.type = "image/*"
        startActivityForResult(Intent.createChooser(choosePictureIntent, "Select Recipe"), REQUEST_CHOOSE_PHOTO)
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (takePictureIntent.resolveActivity(packageManager) != null) {
            var photoFile: File? = null

            try {
                photoFile = createImageFile()
            } catch (e: IOException) {
                // error
            }

            if (photoFile != null) {
                val photoUri = FileProvider.getUriForFile(this, "io.github.mcasper3.prep", photoFile)

                startActivityForResult(takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri), REQUEST_TAKE_PHOTO)
            }
        } else {
            Snackbar.make(rootView, R.string.camera_not_found, Snackbar.LENGTH_SHORT)
        }
    }

    private fun createImageFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val imageFileName = "RECIPE_" + timestamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        val image = File.createTempFile(imageFileName, ".jpg", storageDir)

        currentImagePath = image.absolutePath

        return image
    }

    private fun processImage() = currentImagePath?.let { imagePath ->
        BitmapFactory().resize(imagePath, MAX_FILE_SIZE)

        val myBitmap = BitmapFactory.decodeFile(File(imagePath).absolutePath)

        previewImage.setImageBitmap(myBitmap)

        disposables.add(
                presenter.processImage(File(imagePath))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            when (it) {
                                is InProgressUiModel -> showLoading()
                                is FailureUiModel -> showError(it)
                                is ParseSuccessUiModel -> showParseResults(it.ocrResponse)
                            }
                        }
        )
    }

    companion object {
        internal const val REQUEST_TAKE_PHOTO = 1
        internal const val REQUEST_CHOOSE_PHOTO = 2
        internal const val REQUEST_READ_EXTERNAL = 3
        internal const val MAX_FILE_SIZE = 1024 * 1024

        fun createIntent(context: Context): Intent = Intent(context, CameraActivity::class.java)
    }
}
