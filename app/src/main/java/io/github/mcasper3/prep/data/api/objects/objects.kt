package io.github.mcasper3.prep.data.api.objects

data class Line constructor(val lines: Array<Word>, val maxHeight: Int, val minTop: Int)

data class LineJson constructor(val Lines: Array<Word>, val MaxHeight: Int, val minTop: Int)

data class OcrResponse constructor(val parsedResults: Array<ParsedResult>, val ocrExitCode: String,
                                   val isErroredOnProcessing: Boolean, val errorMessage: String,
                                   val errorDetails: String, val processingTimeInMs: Long)

data class OcrResponseJson constructor(val ParsedResults: Array<ParsedResult>, val OCRExitCode: String,
                                   val IsErroredOnProcessing: Boolean, val ErrorMessage: String,
                                   val ErrorDetails: String, val ProcessingTimeInMilliseconds: Long)

data class ParsedResult constructor(val textOverlay: TextOverlay, val fileParseExitCode: String,
                                    val parsedText: String, val errorMessage: String, val errorDetails: String)

data class ParsedResultJson constructor(val textOverlay: TextOverlay, val fileParseExitCode: String,
                                    val parsedText: String, val errorMessage: String, val errorDetails: String)

data class TextOverlay constructor(val lines: Array<Line>, val hasOverlay: Boolean, val message: String)

data class Word constructor(val wordText: String, val left: Int, val top: Int, val height: Int, val width: Int)

data class ImgurResponse constructor(val data: ImageData, val success: Boolean, val status: Int)

data class ImageData constructor(val id: String, val deleteHash: String, val url: String)