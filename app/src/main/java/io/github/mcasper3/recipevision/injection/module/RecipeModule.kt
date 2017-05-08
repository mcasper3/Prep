package io.github.mcasper3.recipevision.injection.module

import dagger.Module
import dagger.Provides
import io.github.mcasper3.recipevision.data.database.recipe.RecipeDao
import io.github.mcasper3.recipevision.data.sources.RecipeDataSource
import io.github.mcasper3.recipevision.ui.recipes.RecipePresenter

@Module
class RecipeModule {
    @Provides
    fun provideRecipePresenter(recipeDataSource: RecipeDataSource): RecipePresenter {
        return RecipePresenter(recipeDataSource)
    }

    @Provides
    fun provideRecipeDataSource(): RecipeDataSource {
        return RecipeDao()
    }
}