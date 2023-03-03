package com.asdeporte.asdeportev2.ui.reusableview.profile

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.databinding.CreditCardBackViewBinding
import com.asdeporte.asdeportev2.databinding.CreditCardFrontViewBinding

class CreditCardFrontView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private lateinit var binding: CreditCardFrontViewBinding

    init {
        binding = CreditCardFrontViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData() {
        //binding.numberEvents.text = events

    }

}
class CreditCardBackView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private lateinit var binding: CreditCardBackViewBinding

    init {
        binding = CreditCardBackViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData() {
        //binding.numberEvents.text = events

    }

}