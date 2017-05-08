package io.github.mcasper3.recipevision

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import io.github.mcasper3.recipevision.injection.component.ApplicationComponent
import io.github.mcasper3.recipevision.injection.component.DaggerApplicationComponent
import io.github.mcasper3.recipevision.injection.component.RecipeComponent
import io.github.mcasper3.recipevision.injection.module.ApplicationModule
import io.github.mcasper3.recipevision.injection.module.RecipeModule

class RecipeVisionApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
    lateinit var recipeComponent: RecipeComponent

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }

        LeakCanary.install(this)

        applicationComponent = createApplicationComponent()
    }

    private fun createApplicationComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    fun createActivityComponent(): RecipeComponent {
        recipeComponent = applicationComponent.plus(RecipeModule())
        return recipeComponent
    }
}