package com.asdeporte.asdeportev2.utils

import android.content.res.Resources
import java.text.SimpleDateFormat
import java.util.*


fun Long.getStringDate(pattern: String = "dd/MM/yyyy"): String =
    SimpleDateFormat(pattern, Locale(Locale.getDefault().language, "ES")).format(Date(this))

fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

fun pxToDp(px: Int): Int {
    return (px / Resources.getSystem().displayMetrics.density).toInt()
}