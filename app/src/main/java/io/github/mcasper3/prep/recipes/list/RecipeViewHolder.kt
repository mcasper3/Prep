package io.github.mcasper3.prep.recipes.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import io.github.mcasper3.prep.R
import kotterknife.bindView

class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val recipeName: TextView by bindView(R.id.recipe_name)
    private val recipeCookTime: TextView by bindView(R.id.recipe_cook_time)
    private val recipePrepTime: TextView by bindView(R.id.recipe_prep_time)

    fun bind(recipe: RecipeListItem) {
        recipeName.text = recipe.name
        recipeCookTime.text = recipe.cookTime
        recipePrepTime.text = recipe.prepTime
    }
}
