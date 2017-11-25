package io.github.mcasper3.prep.recipes.list

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.base.recycler.RecyclerConfiguration
import io.github.mcasper3.prep.injection.scope.FragmentScope
import io.github.mcasper3.prep.recipes.list.creation.CreationOptionsBottomSheetDialogFragment

@Module
abstract class RecipeListModule {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun taskDetailFragment(): CreationOptionsBottomSheetDialogFragment

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideRecyclerConfiguration() = RecyclerConfiguration(
            R.string.no_recipes,
            R.string.failed_to_load_recipes,
            R.drawable.empty_state,
            true,
            0
        )
    }
}
