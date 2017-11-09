package io.github.mcasper3.prep.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.github.mcasper3.prep.data.database.ingredient.Ingredient
import io.github.mcasper3.prep.data.database.ingredient.IngredientDao
import io.github.mcasper3.prep.data.database.recipe.Recipe
import io.github.mcasper3.prep.data.database.recipe.RecipeDao
import io.github.mcasper3.prep.data.database.step.Step
import io.github.mcasper3.prep.data.database.step.StepDao

@Database(
    entities = arrayOf(
        Recipe::class,
        Step::class,
        Ingredient::class
    ),
    version = 1,
    exportSchema = false
)
abstract class PrepDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun stepDao(): StepDao
    abstract fun ingredientDao(): IngredientDao
}
