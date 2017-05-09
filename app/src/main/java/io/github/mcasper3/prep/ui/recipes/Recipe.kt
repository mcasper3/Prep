package io.github.mcasper3.prep.ui.recipes

class Recipe(private val recipe: RecipePresentationModel) {

    fun bind(viewHolder: RecipeViewHolder) {
        viewHolder.recipeName.text = recipe.name
        viewHolder.recipeTime.text = recipe.totalTime
    }
}