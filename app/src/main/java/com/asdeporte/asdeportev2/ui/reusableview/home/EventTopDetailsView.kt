package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.EventTopDetailsViewBinding
import com.asdeporte.asdeportev2.databinding.MyResumeHomeViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class EventTopDetailsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private lateinit var binding: EventTopDetailsViewBinding

    init {
        binding = EventTopDetailsViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(events: String) {

        //binding.numberEvents.text = events

    }

}