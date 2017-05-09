package io.github.mcasper3.prep.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.mcasper3.prep.data.DataManagerImpl
import io.github.mcasper3.prep.data.sources.DataManager
import io.github.mcasper3.prep.injection.scope.ApplicationContext
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideDataManager(): DataManager {
        return DataManagerImpl()
    }
}