package io.github.mcasper3.prep.recipeviewer

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.mcasper3.prep.base.PrepActivity
import io.github.mcasper3.prep.base.pager.FragmentProvider
import io.github.mcasper3.prep.base.pager.PagerDataProvider
import io.github.mcasper3.prep.injection.scope.ActivityScope
import io.github.mcasper3.prep.recipeviewer.part.RecipePart
import io.github.mcasper3.prep.recipeviewer.part.RecipePartFragment

@Module
abstract class RecipeViewerModule {

    @Binds
    internal abstract fun providePagerDataProvider(dataProvider: RecipePartDataProvider): PagerDataProvider<@JvmWildcard RecipePart>

    @Binds
    internal abstract fun providePrepActivity(activity: RecipeViewerActivity): PrepActivity<*, *>

    @Module
    companion object {

        @Provides
        @JvmStatic
        @ActivityScope
        internal fun provideFragmentProvider(): FragmentProvider<RecipePart, RecipePartFragment> {
            return object : FragmentProvider<RecipePart, RecipePartFragment> {
                override fun newInstance(item: RecipePart) = RecipePartFragment.newInstance(item)
            }
        }
    }
}
