package io.github.mcasper3.prep.recipes.list.creation

import io.github.mcasper3.prep.base.Presenter
import javax.inject.Inject

class CreationOptionsPresenter @Inject constructor() : Presenter<CreationOptionsView>() {

    fun onWriteRecipeClicked() {
        checkIfViewAttached()

        view?.goToWriteRecipe()
    }

    fun onTakePhotoClicked() {
        checkIfViewAttached()

        view?.goToTakePhoto()
    }

    fun onChoosePhotoClicked() {
        checkIfViewAttached()

        view?.goToChoosePhoto()
    }

    fun onAddLinkClicked() {
        checkIfViewAttached()

        view?.goToAddLink()
    }
}