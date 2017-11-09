package io.github.mcasper3.prep.data.database.recipe

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Relation
import io.github.mcasper3.prep.data.database.step.Step
import io.github.mcasper3.prep.data.database.step.StepWithIngredients

data class RecipeWithSteps(
    @ColumnInfo(name = "_id") val id: Long,
    val name: String,
    @ColumnInfo(name = "cook_time") val cookTime: String,
    @ColumnInfo(name = "prep_time") val prepTime: String
) {
    @Relation(parentColumn = "_id", entityColumn = "recipe_id", entity = Step::class)
    lateinit var steps: List<StepWithIngredients>
}
