package io.github.mcasper3.prep.recipes.camera

import io.github.mcasper3.prep.data.api.ocr.OcrResponse
import okhttp3.MediaType
import okhttp3.MultipartBody
import java.io.File

class ParseRequest(image: File) {
    val image: MultipartBody.Part

    init {
        val file = MultipartBody.create(MediaType.parse("image/*"), image)
        this.image = MultipartBody.Part.createFormData("image", image.name, file)
    }
}

class ParseResponse(
        val status: ParseResponseStatus,
        val errorMessage: String? = null,
        val ocrResponse: OcrResponse? = null
) {
    companion object {
        fun inProgress() = ParseResponse(ParseResponseStatus.IN_PROGRESS)
        fun failure(errorMessage: String) = ParseResponse(ParseResponseStatus.FAILURE, errorMessage)
        fun success(ocrResponse: OcrResponse) = ParseResponse(ParseResponseStatus.SUCCESS, ocrResponse = ocrResponse)
    }
}

enum class ParseResponseStatus {
    IN_PROGRESS,
    SUCCESS,
    FAILURE
}
