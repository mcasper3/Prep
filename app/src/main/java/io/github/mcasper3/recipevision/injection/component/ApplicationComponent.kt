package io.github.mcasper3.recipevision.injection.component

import dagger.Component
import io.github.mcasper3.recipevision.injection.module.ApplicationModule
import io.github.mcasper3.recipevision.injection.module.RecipeModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun plus(comicModule: RecipeModule): RecipeComponent
}