package io.github.mcasper3.prep.data

import io.github.mcasper3.prep.data.api.PrepApi
import io.github.mcasper3.prep.data.api.UiModel
import io.github.mcasper3.prep.data.database.PrepDatabase
import io.github.mcasper3.prep.data.sources.DataManager
import io.github.mcasper3.prep.recipes.camera.ParseRequest
import io.github.mcasper3.prep.recipes.camera.ParseSuccessUiModel
import io.reactivex.Observable
import javax.inject.Inject

class DataManagerImpl @Inject constructor(
    private val api: PrepApi,
    private val prepDatabase: PrepDatabase
) : DataManager {
    override fun parseRecipe(parseRequest: ParseRequest): Observable<UiModel> = api.parseImage(parseRequest.image)
        .map { ParseSuccessUiModel(it) }
}
