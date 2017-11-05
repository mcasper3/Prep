package io.github.mcasper3.prep.data.database.step

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import io.github.mcasper3.prep.data.database.recipe.Recipe

@Entity(
    tableName = "steps",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Recipe::class,
            parentColumns = arrayOf("_id"),
            childColumns = arrayOf("recipe_id"),
            onDelete = CASCADE
        )
    ),
    indices = arrayOf(
        Index(
            value = "recipe_id"
        )
    )
)
data class Step(
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String,
    @ColumnInfo(name = "step_number") val stepNumber: Int,
    @ColumnInfo(name = "recipe_id") val recipeId: Int
)
