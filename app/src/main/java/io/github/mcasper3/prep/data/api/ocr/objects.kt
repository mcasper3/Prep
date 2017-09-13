package io.github.mcasper3.prep.data.api.ocr

import com.squareup.moshi.Json

data class Line constructor(
        @Json(name = "Lines") val lines: List<Word>,
        @Json(name = "MaxHeight") val maxHeight: Int,
        @Json(name = "MinTop") val minTop: Int
)

data class OcrResponse constructor(
        @Json(name = "ParsedResults") val parsedResults: List<ParsedResult>,
        @Json(name = "OCRExitCode") val ocrExitCode: String,
        @Json(name = "IsErroredOnProcessing") val isErroredOnProcessing: Boolean,
        @Json(name = "ErrorMessage") val errorMessage: List<String>,
        @Json(name = "ErrorDetails") val errorDetails: String,
        @Json(name = "ProcessingTimeInMilliseconds") val processingTimeInMs: Long
)

data class ParsedResult constructor(
        @Json(name = "TextOverlay") val textOverlay: TextOverlay,
        @Json(name = "FileParseExitCode") val fileParseExitCode: String,
        @Json(name = "ParsedText") val parsedText: String,
        @Json(name = "ErrorMessage") val errorMessage: String,
        @Json(name = "ErrorDetails") val errorDetails: String
)

data class TextOverlay constructor(
        @Json(name = "Lines") val lines: List<Line>,
        @Json(name = "HasOverlay") val hasOverlay: Boolean,
        @Json(name = "Message") val message: String
)

data class Word constructor(
        @Json(name = "WordText") val wordText: String,
        @Json(name = "Left") val left: Int,
        @Json(name = "Top") val top: Int,
        @Json(name = "Height") val height: Int,
        @Json(name = "Width") val width: Int
)
