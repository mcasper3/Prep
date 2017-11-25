package io.github.mcasper3.prep.data.database.recipe

import io.github.mcasper3.prep.add.RecipeInformation
import io.github.mcasper3.prep.data.database.PrepDatabase
import io.github.mcasper3.prep.data.database.step.StepDatabaseHelper
import io.reactivex.Single
import javax.inject.Inject

class RecipeDatabaseHelper @Inject constructor(
    private val prepDatabase: PrepDatabase,
    private val recipeDao: RecipeDao,
    private val stepDatabaseHelper: StepDatabaseHelper
) {

    fun insertRecipe(recipeInformation: RecipeInformation): Single<Recipe> = Single.create(
        { emitter ->
            try {
                val recipeToInsert = Recipe(
                    name = recipeInformation.recipeName,
                    cookTime = recipeInformation.cookTime,
                    prepTime = recipeInformation.prepTime
                )
                val id = recipeDao.insert(recipeToInsert)

                recipeDao.updateAll(Recipe(1, "", "", ""))

                stepDatabaseHelper.updateRecipeId(id)

                emitter.onSuccess(recipeToInsert)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    )

    fun updateRecipe(recipe: io.github.mcasper3.prep.recipeviewer.Recipe): Single<Recipe> {
        prepDatabase.beginTransaction()
        val recipeToInsert = Recipe(name = recipe.name, cookTime = recipe.cookTime, prepTime = recipe.prepTime)
        try {
            val id = recipeDao.insert(recipeToInsert)

            recipe.steps.forEachIndexed { i, step ->
                //                val stepId = stepDao.insert(Step(text = step.text, stepNumber = i + 1, recipeId = id))

//                ingredientDao.insertAll(
//                    *step.ingredients
//                        .map { Ingredient(amount = it.amount, unit = it.unit, name = it.name, stepId = stepId) }
//                        .toTypedArray()
//                )
            }

            prepDatabase.setTransactionSuccessful()
        } finally {
            prepDatabase.endTransaction()
        }

        return Single.just(recipeToInsert)
    }
}
