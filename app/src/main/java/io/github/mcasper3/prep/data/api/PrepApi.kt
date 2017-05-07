package io.github.mcasper3.prep.data.api

import io.github.mcasper3.prep.data.api.objects.ImgurResponse
import io.github.mcasper3.prep.data.api.objects.OcrResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PrepApi {
    @Multipart
    @POST("/parse/image")
    fun parseImage(@Part image: MultipartBody.Part): Observable<OcrResponse>

    @Multipart
    @POST("image")
    fun uploadImage(
            @Header("Authorization") auth: String,
            @Part image: MultipartBody.Part
    ): Observable<ImgurResponse>
}
