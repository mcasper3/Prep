package io.github.mcasper3.prep.recipeviewer

import io.github.mcasper3.prep.base.pager.PagerDataProvider
import io.github.mcasper3.prep.injection.scope.ActivityScope
import io.github.mcasper3.prep.recipeviewer.part.RecipePart
import javax.inject.Inject

@ActivityScope
class RecipePartDataProvider @Inject constructor() : PagerDataProvider<RecipePart> {
    override var data: List<RecipePart> = emptyList()
}
