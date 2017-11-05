package io.github.mcasper3.prep.data.database.recipe

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Update

@Dao
interface RecipeDao {
    @Insert
    fun insertAll(vararg recipes: Recipe)

    @Update
    fun updateAll(vararg recipes: Recipe)

    @Delete
    fun delete(recipe: Recipe)
}
