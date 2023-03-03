package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.MyNextRaceViewBinding
import com.bumptech.glide.Glide

class MyNextRaceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private lateinit var binding: MyNextRaceViewBinding

    init {
        binding = MyNextRaceViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(event: String) {
        //binding.numberEvents.text = events

        Glide.with(this)
            .load("https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2022%2F5%2Fimg_1655144427045_TUP-Marquesa-22-logo-nvo-jun-13.JPG")
            .centerCrop()
            .into(binding.eventImage)
    }

}