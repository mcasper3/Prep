package io.github.mcasper3.prep.data.sources

import io.reactivex.Observable
import okhttp3.MultipartBody

interface RecipeDataSource {
    fun parseRecipe(image: MultipartBody.Part): Observable<Boolean>
}