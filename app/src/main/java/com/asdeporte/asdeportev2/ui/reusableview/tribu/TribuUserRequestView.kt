package com.asdeporte.asdeportev2.ui.reusableview.tribu

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.TribuUserRequestViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.button.MaterialButton

class TribuUserRequestView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    private lateinit var binding: TribuUserRequestViewBinding

    init {
        binding = TribuUserRequestViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun bind(item: EventData, hideButtons: Boolean) {

        Glide.with(this)
            .load("https://picsum.photos/600/900")
            .centerCrop()
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true))
            .into(binding.tribuLogo)

        binding.rejectButton.setOnClickListener {
            binding.actionButtons.visibility = View.INVISIBLE
        }

        binding.acceptButton.setOnClickListener {
            binding.actionButtons.visibility = View.INVISIBLE
        }

        if (hideButtons) {
            binding.actionButtons.visibility = View.INVISIBLE
        }

    }

    fun rejectButton(): MaterialButton {
        return binding.rejectButton
    }

    fun hideButtons() {
        binding.actionButtons.visibility = View.INVISIBLE
    }
}
