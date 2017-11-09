package io.github.mcasper3.prep.data.database.ingredient

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import io.github.mcasper3.prep.data.database.step.Step

@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Step::class,
            parentColumns = arrayOf("_id"),
            childColumns = arrayOf("step_id"),
            onDelete = CASCADE
        )
    ),
    indices = arrayOf(
        Index(
            value = "step_id"
        )
    ),
    tableName = "ingredients"
)
data class Ingredient(
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val amount: Int,
    val unit: Int,
    val name: String,
    @ColumnInfo(name = "step_id") val stepId: Long
)
