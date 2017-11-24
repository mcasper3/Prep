package io.github.mcasper3.prep.add

import dagger.Module
import dagger.Provides
import io.github.mcasper3.prep.base.recycler.RecyclerConfiguration

@Module
class AddRecipeModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideRecyclerConfiguration() = RecyclerConfiguration(isSwipeRefreshEnabled = false)
    }
}
