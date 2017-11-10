package io.github.mcasper3.prep.recipes.list

import io.github.mcasper3.prep.base.LceView
import io.github.mcasper3.prep.data.api.UiModel

interface RecipeListView : LceView {
    fun updateUi(uiModel: UiModel)
}
