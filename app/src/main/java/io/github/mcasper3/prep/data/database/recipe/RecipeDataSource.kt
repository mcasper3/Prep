package io.github.mcasper3.prep.data.database.recipe

import io.github.mcasper3.prep.recipes.list.RecipeListItem
import io.reactivex.Flowable
import javax.inject.Inject

class RecipeDataSource @Inject constructor(
    private val recipeDao: RecipeDao
) {
    fun getAllRecipes(): Flowable<List<RecipeListItem>> {
        return recipeDao.getAll()
            .flatMap {
                Flowable.just(it.map { RecipeListItem(it.name, it.cookTime) })
            }
    }
}
