package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.SerialsHomeViewBinding
import com.bumptech.glide.Glide

class SerialsGridView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: SerialsHomeViewBinding? = null

    init {
        binding = SerialsHomeViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(events: String) {
        binding?.apply {
            Glide.with(context)
                .load(R.drawable.serial_dummy)
                .into(imgLogo)
            Glide.with(context)
                .load(R.drawable.serial_dummy)
                .into(imgLogo2)
            Glide.with(context)
                .load(R.drawable.serial_dummy)
                .into(imgLogo3)
            Glide.with(context)
                .load(R.drawable.serial_dummy)
                .into(imgLogo4)
            Glide.with(context)
                .load(R.drawable.serial_dummy)
                .into(imgLogo5)
            Glide.with(context)
                .load(R.drawable.serial_dummy)
                .into(imgLogo6)
            Glide.with(context)
                .load(R.drawable.serial_dummy)
                .into(imgLogo7)
            Glide.with(context)
                .load(R.drawable.serial_dummy)
                .into(imgLogo8)
            Glide.with(context)
                .load(R.drawable.serial_dummy)
                .into(imgLogo9)
            Glide.with(context)
                .load(R.drawable.serial_dummy)
                .into(imgLogo10)
            Glide.with(context)
                .load(R.drawable.serial_dummy)
                .into(imgLogo11)
            Glide.with(context)
                .load(R.drawable.serial_dummy)
                .into(imgLogo12)
            Glide.with(context)
                .load(R.drawable.proximos_serial_dummy)
                .into(imgNext)
        }

    }
}