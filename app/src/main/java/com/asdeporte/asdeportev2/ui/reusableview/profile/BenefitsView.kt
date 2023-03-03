package com.asdeporte.asdeportev2.ui.reusableview.profile

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.databinding.BenefitsViewBinding

class BenefitsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private lateinit var binding: BenefitsViewBinding

    init {
        binding = BenefitsViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData() {
        //binding.numberEvents.text = events

    }

}