package io.github.mcasper3.prep.data.database

import io.github.mcasper3.prep.data.database.ingredient.Ingredient
import io.github.mcasper3.prep.data.database.ingredient.IngredientDao
import io.github.mcasper3.prep.data.database.recipe.Recipe
import io.github.mcasper3.prep.data.database.recipe.RecipeDao
import io.github.mcasper3.prep.data.database.step.Step
import io.github.mcasper3.prep.data.database.step.StepDao
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DatabaseHelper @Inject constructor(
    private val recipeDao: RecipeDao,
    private val stepDao: StepDao,
    private val ingredientDao: IngredientDao,
    private val prepDatabase: PrepDatabase
) {
    fun insertInitialRecipe(recipe: io.github.mcasper3.prep.recipeviewer.Recipe) {
        recipeDao
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
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}
