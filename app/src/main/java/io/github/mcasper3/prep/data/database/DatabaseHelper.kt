package io.github.mcasper3.prep.data.database

import io.github.mcasper3.prep.data.database.recipe.RecipeDatabaseHelper
import io.github.mcasper3.prep.recipeviewer.Recipe
import javax.inject.Inject

class DatabaseHelper @Inject constructor(
    private val recipeDatabaseHelper: RecipeDatabaseHelper
) {
    fun insertInitialRecipe() = recipeDatabaseHelper.insertRecipe(Recipe(mutableListOf(), "", "", ""))
}
