package io.github.mcasper3.prep.recipes.list

import io.github.mcasper3.prep.data.database.recipe.RecipeDao
import io.reactivex.Flowable
import javax.inject.Inject

class RecipeDataSource @Inject constructor(
    private val recipeDao: RecipeDao
) {
    fun getAllRecipes(): Flowable<List<RecipeListItem>> {
        return recipeDao.getAllSavedRecipes()
            .flatMap {
                Flowable.just(it.map { RecipeListItem(it.name, it.cookTime, it.prepTime) })
            }
    }
}
