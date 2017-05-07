package io.github.mcasper3.prep.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.mcasper3.prep.injection.scope.ActivityScope
import io.github.mcasper3.prep.recipes.camera.CameraActivity
import io.github.mcasper3.prep.recipes.camera.CameraModule
import io.github.mcasper3.prep.recipes.list.RecipeListActivity
import io.github.mcasper3.prep.recipes.list.RecipeListActivityModule

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(RecipeListActivityModule::class))
    abstract fun recipeListActivity(): RecipeListActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(CameraModule::class))
    abstract fun cameraActivity(): CameraActivity
}
