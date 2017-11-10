package io.github.mcasper3.prep.recipes.list

class RecipeListItem(private val name: String, private val totalTime: String) {
    fun bind(viewHolder: RecipeViewHolder) {
        viewHolder.recipeName.text = name
        viewHolder.recipeTime.text = totalTime
    }
}
