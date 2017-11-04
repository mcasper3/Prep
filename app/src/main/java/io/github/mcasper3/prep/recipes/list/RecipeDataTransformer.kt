package io.github.mcasper3.prep.recipes.list

import javax.inject.Inject

class RecipeDataTransformer @Inject constructor() {
    fun getRecipes(): Array<Recipe> {
        val recipes: Array<Recipe> = arrayOf(
                Recipe(RecipePresentationModel("Something Awesome", "1 hour")),
                Recipe(RecipePresentationModel("Something Awesome", "1 hour")),
                Recipe(RecipePresentationModel("Something Awesome", "1 hour")),
                Recipe(RecipePresentationModel("Something Awesome", "1 hour")),
                Recipe(RecipePresentationModel("Something Awesome", "1 hour")),
                Recipe(RecipePresentationModel("Something Awesome", "1 hour"))
        )

        return recipes
    }
}
