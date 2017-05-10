package io.github.mcasper3.prep.ui.recipes.list

import io.github.mcasper3.prep.data.sources.DataManager
import javax.inject.Inject

class RecipePresenterImpl @Inject constructor(private val dataManager: DataManager, private val dataTransformer: RecipeDataTransformer) :
        RecipePresenter() {
    override fun getRecipes() {
        checkIfViewAttached()

        view?.showRecipes(dataTransformer.getRecipes())
    }
}