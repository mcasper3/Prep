package io.github.mcasper3.prep.recipes.camera

import dagger.Binds
import dagger.Module
import io.github.mcasper3.prep.base.PrepActivity

@Module
abstract class CameraModule {
    @Binds abstract fun providePrepActivity(cameraActivity: CameraActivity): PrepActivity<*, *>
}
