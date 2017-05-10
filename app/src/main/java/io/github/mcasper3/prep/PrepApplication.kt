package io.github.mcasper3.prep

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.LeakCanary
import io.github.mcasper3.prep.injection.component.ApplicationComponent
import io.github.mcasper3.prep.injection.component.DaggerApplicationComponent
import io.github.mcasper3.prep.injection.module.ApplicationModule

class PrepApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

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

    companion object {
        fun get(context: Context): PrepApplication {
            return context.applicationContext as PrepApplication
        }
    }
}