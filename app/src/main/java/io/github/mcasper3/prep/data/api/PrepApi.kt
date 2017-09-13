package io.github.mcasper3.prep.data.api

import io.github.mcasper3.prep.data.api.ocr.OcrResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PrepApi {
    @Multipart
    @POST("/parse/image")
    fun parseImage(@Part image: MultipartBody.Part): Observable<OcrResponse>
}
