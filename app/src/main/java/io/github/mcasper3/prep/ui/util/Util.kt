package io.github.mcasper3.prep.ui.util

import android.content.Context
import android.util.DisplayMetrics

class Util {
    companion object {
        fun convertPxToDp(px: Float, context: Context): Float {
            val resources = context.resources
            val metrics = resources.displayMetrics
            val dp = px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
            return dp
        }

        fun convertDpToPx(dp: Float, context: Context): Float {
            val resources = context.resources
            val metrics = resources.displayMetrics
            val px = dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
            return px
        }
    }
}