package io.github.mcasper3.prep.data.sources

import io.github.mcasper3.prep.recipes.camera.ParseRequest
import io.github.mcasper3.prep.recipes.camera.ParseResponse
import io.reactivex.Observable

interface RecipeDataSource {
    fun parseRecipe(parseRequest: ParseRequest): Observable<ParseResponse>
}