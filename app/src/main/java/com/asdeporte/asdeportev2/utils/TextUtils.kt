package com.asdeporte.asdeportev2.utils

import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan

class TextUtils {

    fun agregarNegrita(textoCompleto: String, textoNegrita: String): SpannableStringBuilder {
        val builder = SpannableStringBuilder(textoCompleto)
        val start = textoCompleto.indexOf(textoNegrita)
        val end = start + textoNegrita.length
        builder.setSpan(StyleSpan(Typeface.BOLD), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        return builder
    }
}