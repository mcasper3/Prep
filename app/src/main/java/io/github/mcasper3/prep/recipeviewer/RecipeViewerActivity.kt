package io.github.mcasper3.prep.recipeviewer

import android.os.Bundle
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.base.PrepActivity
import io.github.mcasper3.prep.base.pager.PagerAdapter
import io.github.mcasper3.prep.recipeviewer.part.RecipePart
import io.github.mcasper3.prep.recipeviewer.part.RecipePartFragment
import javax.inject.Inject

class RecipeViewerActivity : PrepActivity<RecipeViewerPresenter, RecipeViewerView>() {

    @Inject override lateinit var presenter: RecipeViewerPresenter
    @Inject lateinit var adapter: PagerAdapter<@JvmWildcard RecipePart, @JvmWildcard RecipePartFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_viewer)
    }
}
