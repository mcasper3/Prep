package io.github.mcasper3.recipevision.injection.component

import dagger.Subcomponent
import io.github.mcasper3.recipevision.injection.module.RecipeModule

@Subcomponent(modules = arrayOf(RecipeModule::class))
interface RecipeComponent {

}