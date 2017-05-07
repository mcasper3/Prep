package io.github.mcasper3.prep.common

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

fun Bitmap.toBase64(): String {
    val output = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, output)
    val byteArray = output.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT).trim()
}