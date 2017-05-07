package io.github.mcasper3.prep

import android.content.Context
import android.os.StrictMode
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.github.mcasper3.prep.injection.component.ApplicationComponent
import io.github.mcasper3.prep.injection.component.DaggerApplicationComponent
import io.github.mcasper3.prep.util.ProdTree
import timber.log.Timber

class PrepApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent

    private val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .application(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }

        LeakCanary.install(this)

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDialog()
                .build())
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())

        appComponent.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ProdTree())
        }
    }

    companion object {
        fun get(context: Context): PrepApplication = context.applicationContext as PrepApplication
    }
}