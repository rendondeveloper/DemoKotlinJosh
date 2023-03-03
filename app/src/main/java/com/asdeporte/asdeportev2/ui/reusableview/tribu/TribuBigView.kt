package com.asdeporte.asdeportev2.ui.reusableview.tribu

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.TribuBigViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class TribuBigView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    interface TribuBigViewListener {
        fun onSearch()
        fun onFilters()
    }
    private lateinit var listener: TribuBigViewListener

    private lateinit var binding: TribuBigViewBinding

    init {
        binding = TribuBigViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData() {
        //binding.numberEvents.text = events
        //this.listener = listener

        var requestOptions = RequestOptions()
            .placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder_img))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(4))
        Glide.with(this)
            .load("https://picsum.photos/600/600")
            .fitCenter()
            .apply(requestOptions)
            .into(binding.tribuLogo)

    }

}