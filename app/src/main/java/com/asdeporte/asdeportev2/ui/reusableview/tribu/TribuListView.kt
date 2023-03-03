package com.asdeporte.asdeportev2.ui.reusableview.tribu

import android.content.Context
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.TribuListViewBinding
import com.bumptech.glide.Glide

class TribuListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private lateinit var binding: TribuListViewBinding

    private var selectedTribu = false

    init {
        binding = TribuListViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData() {

        Glide.with(this)
            .load("https://picsum.photos/100/100")
            .centerCrop()
            .into(binding.tribuImage)

        binding.cardView.setOnClickListener {
            if (selectedTribu) {
                selectedTribu = false
                binding.selectedButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_off))
                binding.selectedButton.setColorFilter(ContextCompat.getColor(context, R.color.gray_600), PorterDuff.Mode.SRC_IN)
            } else {
                selectedTribu = true
                binding.selectedButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_on))
                binding.selectedButton.setColorFilter(ContextCompat.getColor(context, R.color.orange_as_light), PorterDuff.Mode.SRC_IN)
            }
        }
        binding.selectedButton.setOnClickListener {
            if (selectedTribu) {
                selectedTribu = false
                binding.selectedButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_off))
                binding.selectedButton.setColorFilter(ContextCompat.getColor(context, R.color.gray_600), PorterDuff.Mode.SRC_IN)
            } else {
                selectedTribu = true
                binding.selectedButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_on))
                binding.selectedButton.setColorFilter(ContextCompat.getColor(context, R.color.orange_as_light), PorterDuff.Mode.SRC_IN)
            }
        }


    }

}