package io.github.mcasper3.prep.recipes.list

import android.view.View
import android.widget.TextView
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.base.recycler.BaseViewHolder
import kotterknife.bindView

class RecipeViewHolder(itemView: View) : BaseViewHolder<RecipeListItem>(itemView) {
    private val recipeName: TextView by bindView(R.id.recipe_name)
    private val recipeCookTime: TextView by bindView(R.id.recipe_cook_time)
    private val recipePrepTime: TextView by bindView(R.id.recipe_prep_time)

    override fun bind(item: RecipeListItem) {
        recipeName.text = item.name
        recipeCookTime.text = item.cookTime
        recipePrepTime.text = item.prepTime
    }
}
