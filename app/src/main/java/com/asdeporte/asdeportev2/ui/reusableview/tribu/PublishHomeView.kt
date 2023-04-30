package com.asdeporte.asdeportev2.ui.reusableview.tribu

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.databinding.PublishHomeViewBinding

class PublishHomeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    interface PublishHomeViewListener {
        fun onPublishText()
    }
    private lateinit var listener: PublishHomeViewListener

    private lateinit var binding: PublishHomeViewBinding

    init {
        binding = PublishHomeViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(listener: PublishHomeViewListener) {
        //binding.numberEvents.text = events
        this.listener = listener

        binding.thinkingView.setOnClickListener {
            listener.onPublishText()
        }

    }

}