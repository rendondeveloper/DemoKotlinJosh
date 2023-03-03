package com.asdeporte.asdeportev2.ui.reusableview.inscription

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.PaymentWalletItemViewBinding

class PaymentWalletItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var binding: PaymentWalletItemViewBinding

    private var isActive = false

    init {
        binding = PaymentWalletItemViewBinding.inflate(LayoutInflater.from(context), this, true)
        setupListener()
    }

    private fun setupListener() {
        binding.cardView.setOnClickListener {
            if (isActive) {
                isActive = false
                binding.cardView.background = ContextCompat.getDrawable(context, R.drawable.gray_border_corner_3)
            } else {
                isActive = true
                binding.cardView.background = ContextCompat.getDrawable(context, R.drawable.kit_item_selected)

            }
        }

    }


}
