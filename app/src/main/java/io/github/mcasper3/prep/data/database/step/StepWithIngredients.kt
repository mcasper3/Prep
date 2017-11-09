package io.github.mcasper3.prep.data.database.step

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Relation
import io.github.mcasper3.prep.data.database.ingredient.Ingredient

data class StepWithIngredients(
    @ColumnInfo(name = "_id") val id: Long,
    val text: String,
    @ColumnInfo(name = "step_number") val stepNumber: Int,
    @ColumnInfo(name = "recipe_id") val recipeId: Long
) {
    @Relation(parentColumn = "_id", entityColumn = "step_id") lateinit var ingredients: List<Ingredient>
}
