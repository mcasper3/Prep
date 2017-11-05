package io.github.mcasper3.prep.injection.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import io.github.mcasper3.prep.injection.AppContext

@Module
abstract class ApplicationModule {
    @Binds
    @AppContext
    abstract fun provideAppContext(app: Application): Context
}
