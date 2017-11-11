package io.github.mcasper3.prep.recipes.camera

import io.github.mcasper3.prep.base.Presenter
import javax.inject.Inject

class CameraPresenter @Inject constructor() : Presenter<CameraView>() {
    /*fun processImage(image: File): Observable<UiModel> {
        return dataManager.parseRecipe(ParseRequest(image))
            .onErrorReturn(
                { t ->
                    Timber.e(t)
                    FailureUiModel(t.localizedMessage)
                }
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .startWith(InProgressUiModel())
    }*/
}
