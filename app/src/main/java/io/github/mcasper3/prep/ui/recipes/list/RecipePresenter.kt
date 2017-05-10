package io.github.mcasper3.prep.ui.recipes.list

import io.github.mcasper3.prep.ui.base.BasePresenter

abstract class RecipePresenter : BasePresenter<RecipeView>() {
    abstract fun getRecipes()
}
