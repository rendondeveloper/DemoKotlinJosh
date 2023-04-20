package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View.OnTouchListener
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
    private var images = arrayOf(
        "https://images.unsplash.com/photo-1499438075715-fc23ef376ab9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1642&q=80",
        "https://images.unsplash.com/photo-1557685888-2d3621ddf615?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=770&q=80",
        "https://images.unsplash.com/photo-1509486432407-f8fb9cc99acd?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=798&q=80",
        "https://images.unsplash.com/photo-1452573992436-6d508f200b30?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1746&q=80",
        "https://images.unsplash.com/photo-1523514931661-85ca60afec15?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80")

    var activeTag = 0

    init {
        binding = SliderHomeViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(events: String) {

        val typefaceRegular = ResourcesCompat.getFont(context, R.font.kanit_regular)
        val typefaceBold = ResourcesCompat.getFont(context, R.font.kanit_bold)
        /*
        Glide.with(this)
            .load(images[activeTag])
            .centerCrop()
            .into(binding.image)
         */
        binding?.apply {
            firstButton.setTextColor(ContextCompat.getColor(context, R.color.white))
            firstButton.setOnClickListener {
                firstButton.setTextColor(ContextCompat.getColor(context, R.color.white))
                firstButton.typeface = typefaceBold
                secondButton.setTextColor(ContextCompat.getColor(context, R.color.white))
                secondButton.typeface = typefaceRegular
                activeTag = 0
                changeImage(activeTag)
            }
            secondButton.setOnClickListener {
                firstButton.setTextColor(ContextCompat.getColor(context, R.color.white))
                firstButton.typeface = typefaceRegular
                secondButton.setTextColor(ContextCompat.getColor(context, R.color.white))
                secondButton.typeface = typefaceBold
                activeTag = 1
                changeImage(activeTag)
            }
        }

    }

    fun changeImage(position: Int) {
        /*
        Glide.with(this)
            .load(images[position])
            .centerCrop()
            .into(binding.image)
         */
    }

}