package io.github.mcasper3.prep.data

import io.github.mcasper3.prep.data.api.PrepApi
import io.github.mcasper3.prep.data.sources.DataManager
import io.reactivex.Observable
import okhttp3.MultipartBody
import javax.inject.Inject

class DataManagerImpl @Inject constructor(
        private val api: PrepApi
): DataManager {
    override fun parseRecipe(image: MultipartBody.Part): Observable<Boolean> = api.parseImage(image).flatMap { Observable.just(true) }
}
