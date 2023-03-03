package com.asdeporte.asdeportev2.ui.reusableview.inscription

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.KitChooiceItemViewBinding
import com.asdeporte.asdeportev2.databinding.PaymentCardItemViewBinding

class PaymentCardItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var binding: PaymentCardItemViewBinding

    private var isActive = false

    init {
        binding = PaymentCardItemViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setupToHome() {

        /*binding.selectButton.setOnClickListener {
            if (isActive) {
                isActive = false
                binding.cardView.background = ContextCompat.getDrawable(context, R.drawable.gray_border)
            } else {
                isActive = true
                binding.cardView.background = ContextCompat.getDrawable(context, R.drawable.kit_item_selected)
            }
        }*/
    }


}
