package io.github.mcasper3.prep.add.step

import dagger.Module
import dagger.Provides
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.base.recycler.RecyclerConfiguration

@Module
abstract class AddStepModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideRecyclerConfiguration() = RecyclerConfiguration(
            0,
            0,
            0,
            false,
            R.layout.view_add_step_top_layout
        )
    }
}
