package io.github.mcasper3.prep.ui.recipes.list

import io.github.mcasper3.prep.ui.base.LceView

interface RecipeView : LceView {
    fun showRecipes(recipes: Array<Recipe>)
}