package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.SliderHomeViewBinding
import com.bumptech.glide.Glide

class SliderHomeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: SliderHomeViewBinding? = null

    init {
        binding = SliderHomeViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(events: String) {

        val typefaceRegular = ResourcesCompat.getFont(context, R.font.kanit_regular)
        val typefaceBold = ResourcesCompat.getFont(context, R.font.kanit_bold)

        binding?.apply {
            Glide.with(context)
                .load(R.drawable.slide_dummy)
                .into(image)
            firstButton.setTextColor(ContextCompat.getColor(context, R.color.white))
            firstButton.setOnClickListener {
                firstButton.setTextColor(ContextCompat.getColor(context, R.color.white))
                firstButton.typeface = typefaceBold
                secondButton.setTextColor(ContextCompat.getColor(context, R.color.white))
                secondButton.typeface = typefaceRegular
            }
            secondButton.setOnClickListener {
                firstButton.setTextColor(ContextCompat.getColor(context, R.color.white))
                firstButton.typeface = typefaceRegular
                secondButton.setTextColor(ContextCompat.getColor(context, R.color.white))
                secondButton.typeface = typefaceBold
            }
        }

    }


}