package io.github.mcasper3.prep.recipes.list

import io.github.mcasper3.prep.base.LceView

interface RecipeView : LceView {
    fun showRecipes(recipes: Array<Recipe>)
}