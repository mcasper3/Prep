package io.github.mcasper3.prep.recipes.camera

import io.github.mcasper3.prep.data.api.SuccessUiModel
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

class ParseSuccessUiModel(val ocrResponse: OcrResponse) : SuccessUiModel()
