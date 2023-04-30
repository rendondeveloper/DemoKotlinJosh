package com.asdeporte.asdeportev2.ui.reusableview.inscription

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.asdeporte.asdeportev2.databinding.PaymentCardItemViewBinding

class PaymentCardItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var binding: PaymentCardItemViewBinding

    init {
        binding = PaymentCardItemViewBinding.inflate(LayoutInflater.from(context), this, true)
    }


}
