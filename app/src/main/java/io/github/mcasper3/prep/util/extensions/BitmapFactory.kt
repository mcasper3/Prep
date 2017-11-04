package io.github.mcasper3.prep.util.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.FileOutputStream

fun BitmapFactory.Options.calculateInSampleSize(requiredSize: Int): Int {
    // Raw height and width of image
    val height = outHeight
    val width = outWidth
    var inSampleSize = 1

    if (height * width * 4 > requiredSize) {

        val halfHeight = height / 2
        val halfWidth = width / 2

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while ((halfHeight / inSampleSize) * (halfWidth / inSampleSize) * 4 > requiredSize) {
            inSampleSize *= 2
        }
    }

    return inSampleSize
}

fun BitmapFactory.resize(imagePath: String, requiredSize: Int) {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFile(imagePath, options)

    options.inSampleSize = options.calculateInSampleSize(requiredSize)
    options.inJustDecodeBounds = false

    val bitmap = BitmapFactory.decodeFile(imagePath, options)

    var fileOutputStream: FileOutputStream? = null
    try {
        fileOutputStream = FileOutputStream(imagePath, false)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
    } finally {
        fileOutputStream?.flush()
        fileOutputStream?.close()
        bitmap.recycle()
    }
}
