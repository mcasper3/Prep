package io.github.mcasper3.prep.data.sources

import io.github.mcasper3.prep.base.UiModel
import io.github.mcasper3.prep.recipes.camera.ParseRequest
import io.reactivex.Observable

interface OcrDataSource {
    fun parseRecipe(parseRequest: ParseRequest): Observable<UiModel>
}
