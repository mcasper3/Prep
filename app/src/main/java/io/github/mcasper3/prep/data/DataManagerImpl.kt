package io.github.mcasper3.prep.data

import io.github.mcasper3.prep.data.api.PrepApi
import io.github.mcasper3.prep.data.sources.DataManager
import io.github.mcasper3.prep.recipes.camera.ParseRequest
import io.github.mcasper3.prep.recipes.camera.ParseResponse
import io.reactivex.Observable
import javax.inject.Inject

class DataManagerImpl @Inject constructor(
        private val api: PrepApi
): DataManager {
    override fun parseRecipe(parseRequest: ParseRequest): Observable<ParseResponse> = api.parseImage(parseRequest.image)
            .map { ParseResponse.success(it) }
}
