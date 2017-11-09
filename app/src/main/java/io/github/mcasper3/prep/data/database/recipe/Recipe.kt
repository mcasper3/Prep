package io.github.mcasper3.prep.data.database.recipe

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    @ColumnInfo(name = "cook_time") val cookTime: String,
    @ColumnInfo(name = "prep_time") val prepTime: String
)
