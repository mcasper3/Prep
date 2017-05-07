package io.github.mcasper3.prep.recipes.camera

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.speech.tts.TextToSpeech
import android.support.media.ExifInterface
import android.support.v4.content.FileProvider
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.base.PrepActivity
import io.github.mcasper3.prep.util.PermissionHelper
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CameraActivity : PrepActivity<CameraPresenter, CameraView>(), CameraView {
    private val REQUEST_TAKE_PHOTO = 1
    private val REQUEST_CHOOSE_PHOTO = 2
    private val REQUEST_READ_EXTERNAL = 3

    @Inject override lateinit var presenter: CameraPresenter
    @Inject lateinit var permissionHelper: PermissionHelper

    private val mTakePhotoButton: Button by bindView(R.id.take_photo)
    private val mChoosePhotoButton: Button by bindView(R.id.choose_photo)
    private val mImage: ImageView by bindView(R.id.selected)
    private val mText: TextView by bindView(R.id.textToShow)

    // A TextToSpeech engine for speaking a String value.
    private var tts: TextToSpeech? = null
    private var mCurrentImagePath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        // Set up the Text To Speech engine.
        val listener = TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                Log.d("OnInitListener", "Text to speech engine started successfully.")
                tts?.language = Locale.US
            } else {
                Log.d("OnInitListener", "Error starting the text to speech engine.")
            }
        }
        tts = TextToSpeech(this.applicationContext, listener)

        mTakePhotoButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

        mChoosePhotoButton.setOnClickListener {
            if (permissionHelper.hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    REQUEST_READ_EXTERNAL, R.string.read_external_rationale)) {

                dispatchChoosePictureIntent()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (mCurrentImagePath != null) {
            outState.putString("path", mCurrentImagePath)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        mCurrentImagePath = savedInstanceState?.getString("path")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && (requestCode == REQUEST_TAKE_PHOTO || requestCode == REQUEST_CHOOSE_PHOTO)) {
            var bitmap: Bitmap
            val exif: ExifInterface
            var size: Long = 0
            val inputStream: InputStream
            val secondInputStream: InputStream

                val file = File(mCurrentImagePath)
                size = file.length()

            if (size > 10 * 1024) {
                Log.i("Size", "$size")
            }

            // TODO resize image if needed

            presenter.processImage(File(mCurrentImagePath))
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

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
            }
        }
    }

    private fun createImageFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val imageFileName = "RECIPE_" + timestamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        val image = File.createTempFile(imageFileName, ".jpg", storageDir)

        mCurrentImagePath = image.absolutePath

        return image
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, CameraActivity::class.java)
    }
}
