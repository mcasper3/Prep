package io.github.mcasper3.prep.injection.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import io.github.mcasper3.prep.PrepApplication
import io.github.mcasper3.prep.data.DataModule
import io.github.mcasper3.prep.injection.module.ActivityBindingModule
import io.github.mcasper3.prep.injection.module.ApplicationModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        DataModule::class,
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class
))
interface ApplicationComponent : AndroidInjector<DaggerApplication> {
    fun inject(app: PrepApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): ApplicationComponent.Builder

        fun build(): ApplicationComponent
    }
}