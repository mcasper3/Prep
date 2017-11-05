package io.github.mcasper3.prep.recipes.camera

import io.github.mcasper3.prep.base.LceView
import io.github.mcasper3.prep.data.api.ocr.OcrResponse

interface CameraView : LceView {
    fun showParseResults(result: OcrResponse)
}
