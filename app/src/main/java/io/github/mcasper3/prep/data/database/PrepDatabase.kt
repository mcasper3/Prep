package io.github.mcasper3.prep.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.github.mcasper3.prep.data.database.recipe.Recipe
import io.github.mcasper3.prep.data.database.recipe.RecipeDao
import io.github.mcasper3.prep.data.database.step.Step

@Database(
    entities = arrayOf(
        Recipe::class,
        Step::class
    ),
    version = 1,
    exportSchema = false
)
abstract class PrepDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}
