package io.github.mcasper3.prep.injection.module

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.mcasper3.prep.data.sources.DataManager
import io.github.mcasper3.prep.injection.scope.ActivityContext
import io.github.mcasper3.prep.ui.recipes.RecipeDataTransformer
import io.github.mcasper3.prep.ui.recipes.RecipePresenter
import io.github.mcasper3.prep.ui.recipes.RecipePresenterImpl

@Module
class ActivityModule(private val activity: Activity) {
    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideRecipePresenter(dataManager: DataManager, dataTransformer: RecipeDataTransformer): RecipePresenter {
        return RecipePresenterImpl(dataManager, dataTransformer)
    }
}