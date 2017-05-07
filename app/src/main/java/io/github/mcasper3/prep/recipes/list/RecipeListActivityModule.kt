package io.github.mcasper3.prep.recipes.list

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.mcasper3.prep.injection.scope.FragmentScope
import io.github.mcasper3.prep.recipes.list.creation.CreationOptionsBottomSheetDialogFragment

@Module
abstract class RecipeListActivityModule {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun taskDetailFragment(): CreationOptionsBottomSheetDialogFragment
}
