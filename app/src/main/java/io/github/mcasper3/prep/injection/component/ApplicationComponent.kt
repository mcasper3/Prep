package io.github.mcasper3.prep.injection.component

import dagger.Component
import io.github.mcasper3.prep.data.sources.DataManager
import io.github.mcasper3.prep.injection.module.ApplicationModule
import io.github.mcasper3.prep.injection.module.ActivityModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun getDataManager(): DataManager
    fun plus(comicModule: ActivityModule): ActivityComponent
}