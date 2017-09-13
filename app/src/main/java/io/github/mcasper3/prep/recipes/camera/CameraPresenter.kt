package io.github.mcasper3.prep.recipes.camera

import io.github.mcasper3.prep.base.Presenter
import io.github.mcasper3.prep.data.sources.DataManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.File
import javax.inject.Inject

class CameraPresenter @Inject constructor(private val dataManager: DataManager): Presenter<CameraView>() {
    fun processImage(image: File): Observable<ParseResponse> {
        return dataManager.parseRecipe(ParseRequest(image))
                .onErrorReturn({ t ->
                    Timber.e(t)
                    ParseResponse.failure(t.localizedMessage)
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(ParseResponse.inProgress())
    }
}
