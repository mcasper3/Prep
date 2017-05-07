package io.github.mcasper3.prep.recipes.camera

import io.github.mcasper3.prep.base.Presenter
import io.github.mcasper3.prep.data.sources.DataManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject

class CameraPresenter @Inject constructor(private val dataManager: DataManager): Presenter<CameraView>() {
    fun processImage(base64Image: File) {
        checkIfViewAttached()

        val file = MultipartBody.create(MediaType.parse("image/*"), base64Image)
        val body = MultipartBody.Part.createFormData("image", base64Image.name, file)

        dataManager.parseRecipe(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                },{

                })
    }
}
