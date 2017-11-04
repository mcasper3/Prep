package io.github.mcasper3.prep.recipes.list

import io.github.mcasper3.prep.base.Presenter
import io.github.mcasper3.prep.data.sources.DataManager
import javax.inject.Inject

class RecipePresenter @Inject constructor(
        private val dataManager: DataManager,
        private val dataTransformer: RecipeDataTransformer
) : Presenter<RecipeView>() {
    fun getRecipes() = checkIfViewAttached { view?.showRecipes(dataTransformer.getRecipes()) }
}
