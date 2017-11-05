package io.github.mcasper3.prep.data.database.recipe

import io.github.mcasper3.prep.data.api.UiModel
import io.github.mcasper3.prep.data.sources.RecipeDataSource
import io.github.mcasper3.prep.recipes.camera.ParseRequest
import io.reactivex.Observable

class RecipeDao : RecipeDataSource {
    override fun parseRecipe(parseRequest: ParseRequest): Observable<UiModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
