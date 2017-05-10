package io.github.mcasper3.prep.ui.recipes.list

class Recipe(private val recipe: RecipePresentationModel) {

    fun bind(viewHolder: RecipeViewHolder) {
        viewHolder.recipeName.text = recipe.name
        viewHolder.recipeTime.text = recipe.totalTime
    }
}