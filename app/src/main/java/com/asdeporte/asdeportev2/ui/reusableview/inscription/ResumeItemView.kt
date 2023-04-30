package com.asdeporte.asdeportev2.ui.reusableview.inscription

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.ResumeItemViewBinding

class ResumeItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var binding: ResumeItemViewBinding

    init {
        binding = ResumeItemViewBinding.inflate(LayoutInflater.from(context), this, true)
        setupListener()
    }

    private fun setupListener() {


    }

    fun isTotal() {
        binding.cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.black_dynamic))
        binding.titleText.setTextColor(ContextCompat.getColor(context, R.color.white_dynamic))
        binding.titleText.text = "TOTAL:"
        binding.descriptionText.visibility = View.GONE
        binding.deleteText.visibility = View.GONE
        binding.priceText.setTextColor(ContextCompat.getColor(context, R.color.white_dynamic))
        binding.priceText.text = "\$1,522.00"
        binding.subpriceText.setTextColor(ContextCompat.getColor(context, R.color.white_dynamic))
    }


}
