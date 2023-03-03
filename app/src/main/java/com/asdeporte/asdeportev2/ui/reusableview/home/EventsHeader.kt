package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.text.TextPaint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.EventHeaderBinding


class EventsHeader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private lateinit var binding: EventHeaderBinding

    init {
        binding = EventHeaderBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setTitle(title: String) {

        val paint: TextPaint = binding.title.paint
        val width = paint.measureText("Tianjin, China")
        val textShader2: Shader = LinearGradient(
            0f, 0f, width, binding.title.textSize, intArrayOf(
                ContextCompat.getColor(context, R.color.asd_pink),
                ContextCompat.getColor(context, R.color.orange_as_light)
            ), null, Shader.TileMode.CLAMP
        )

        binding.title.paint.shader = textShader2

        binding.title.text = title
    }

}