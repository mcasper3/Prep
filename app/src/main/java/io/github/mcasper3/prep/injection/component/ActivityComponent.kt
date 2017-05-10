package io.github.mcasper3.prep.injection.component

import dagger.Subcomponent
import io.github.mcasper3.prep.injection.module.ActivityModule
import io.github.mcasper3.prep.ui.recipes.list.MainActivity

@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(activity: MainActivity)
}