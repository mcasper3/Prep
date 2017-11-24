package io.github.mcasper3.prep.recipes.list

import io.github.mcasper3.prep.base.SuccessUiModel

data class GetRecipeListSuccessUiModel(
    val recipes: List<RecipeListViewHolderFactory>
) : SuccessUiModel()
