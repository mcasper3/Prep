package io.github.mcasper3.prep.recipes.list

import io.github.mcasper3.prep.data.api.SuccessUiModel

data class GetRecipeListSuccessUiModel(
    val recipes: List<RecipeListItem>
) : SuccessUiModel()
