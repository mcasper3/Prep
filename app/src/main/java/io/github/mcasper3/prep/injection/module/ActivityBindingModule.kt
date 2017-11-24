package io.github.mcasper3.prep.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.mcasper3.prep.add.AddRecipeActivity
import io.github.mcasper3.prep.add.AddRecipeModule
import io.github.mcasper3.prep.injection.scope.ActivityScope
import io.github.mcasper3.prep.recipes.camera.CameraActivity
import io.github.mcasper3.prep.recipes.camera.CameraModule
import io.github.mcasper3.prep.recipes.list.RecipeListActivity
import io.github.mcasper3.prep.recipes.list.RecipeListModule
import io.github.mcasper3.prep.recipeviewer.RecipeViewerActivity
import io.github.mcasper3.prep.recipeviewer.RecipeViewerModule

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(RecipeListModule::class))
    abstract fun recipeListActivity(): RecipeListActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(CameraModule::class))
    abstract fun cameraActivity(): CameraActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(RecipeViewerModule::class))
    abstract fun recipeViewerActivity(): RecipeViewerActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(AddRecipeModule::class))
    abstract fun addRecipeActivity(): AddRecipeActivity
}
