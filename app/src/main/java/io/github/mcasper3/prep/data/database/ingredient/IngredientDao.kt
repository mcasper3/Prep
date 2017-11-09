package io.github.mcasper3.prep.data.database.ingredient

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Flowable

@Dao
interface IngredientDao {
    @Query("SELECT * FROM ingredients")
    fun getAll(): Flowable<List<Ingredient>>

    @Insert
    fun insertAll(vararg ingredients: Ingredient)

    @Update
    fun updateAll(vararg ingredient: Ingredient)

    @Delete
    fun delete(ingredient: Ingredient)
}
