package io.github.mcasper3.prep.recipes.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import io.github.mcasper3.prep.R
import kotterknife.bindView

class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val recipeName: TextView by bindView(R.id.recipe_name)
    val recipeTime: TextView by bindView(R.id.recipe_time)
}
