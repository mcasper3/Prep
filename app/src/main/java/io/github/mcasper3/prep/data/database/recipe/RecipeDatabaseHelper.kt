package io.github.mcasper3.prep.data.database.recipe

import io.github.mcasper3.prep.data.database.PrepDatabase
import io.github.mcasper3.prep.data.database.ingredient.Ingredient
import io.github.mcasper3.prep.data.database.ingredient.IngredientDao
import io.github.mcasper3.prep.data.database.step.Step
import io.github.mcasper3.prep.data.database.step.StepDao
import io.reactivex.Single
import javax.inject.Inject

class RecipeDatabaseHelper @Inject constructor(
    private val prepDatabase: PrepDatabase,
    private val recipeDao: RecipeDao,
    private val stepDao: StepDao,
    private val ingredientDao: IngredientDao
) {

    fun insertRecipe(recipe: io.github.mcasper3.prep.recipeviewer.Recipe): Single<Recipe> = recipeDao
        .checkIfRecipeExists(1)
        .onErrorReturn {
            prepDatabase.beginTransaction()
            val recipeToInsert = Recipe(name = recipe.name, cookTime = recipe.cookTime, prepTime = recipe.prepTime)
            try {
                val id = recipeDao.insert(recipeToInsert)

                recipe.steps.forEachIndexed { i, step ->
                    val stepId = stepDao.insert(Step(text = step.text, stepNumber = i + 1, recipeId = id))

                    ingredientDao.insertAll(
                        *step.ingredients
                            .map { Ingredient(amount = it.amount, unit = it.unit, name = it.name, stepId = stepId) }
                            .toTypedArray()
                    )
                }

                prepDatabase.setTransactionSuccessful()
            } finally {
                prepDatabase.endTransaction()
            }

            recipeToInsert
        }
}
