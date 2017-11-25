package io.github.mcasper3.prep.data.database

import io.github.mcasper3.prep.add.RecipeInformation
import io.github.mcasper3.prep.data.database.recipe.Recipe
import io.github.mcasper3.prep.data.database.recipe.RecipeDao
import io.github.mcasper3.prep.data.database.recipe.RecipeDatabaseHelper
import io.reactivex.Single
import javax.inject.Inject

class DatabaseHelper @Inject constructor(
    private val recipeDao: RecipeDao,
    private val recipeDatabaseHelper: RecipeDatabaseHelper
) {
    fun insertInitialRecipe(): Single<Recipe> = recipeDao.checkIfRecipeExists(1)
        .onErrorResumeNext { recipeDatabaseHelper.insertRecipe(RecipeInformation("", "", "")) }
}
