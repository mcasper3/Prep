package io.github.mcasper3.prep.recipes.list.creation

import io.github.mcasper3.prep.base.View

interface CreationOptionsView : View {
    fun goToWriteRecipe()
    fun goToTakePhoto()
    fun goToChoosePhoto()
    fun goToAddLink()
}
