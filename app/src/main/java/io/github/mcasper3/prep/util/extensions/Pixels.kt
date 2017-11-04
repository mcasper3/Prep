package io.github.mcasper3.prep.util.extensions

import android.content.Context
import android.util.DisplayMetrics.DENSITY_DEFAULT

fun Float.toDp(context: Context) = this / (context.resources.displayMetrics.densityDpi.toFloat() / DENSITY_DEFAULT)

fun Float.toPx(context: Context) = this * (context.resources.displayMetrics.densityDpi.toFloat() / DENSITY_DEFAULT)
