package io.github.mcasper3.prep.data.database.recipe

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import android.arch.persistence.room.Update
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes")
    fun getAll(): Flowable<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE _id > 1")
    fun getAllSavedRecipes(): Flowable<List<Recipe>>

    @Query("SELECT * FROM Recipes WHERE _id = :id")
    fun checkIfRecipeExists(id: Long): Single<Recipe>

    @Transaction
    @Query("SELECT * FROM Recipes WHERE _id = :id")
    fun getById(id: Long): Flowable<RecipeWithSteps>

    @Insert
    fun insertAll(vararg recipes: Recipe): List<Long>

    @Insert
    fun insert(recipe: Recipe): Long

    @Update
    fun updateAll(vararg recipes: Recipe)

    @Delete
    fun delete(recipe: Recipe)
}
