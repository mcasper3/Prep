package io.github.mcasper3.prep.ui.recipes

import io.github.mcasper3.prep.ui.base.BasePresenter

abstract class RecipePresenter : BasePresenter<RecipeView>() {
    abstract fun getRecipes()
}
