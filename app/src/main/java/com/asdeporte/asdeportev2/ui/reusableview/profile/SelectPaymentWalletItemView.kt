package com.asdeporte.asdeportev2.ui.reusableview.profile

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.SelectPaymentWalletItemViewBinding
import com.asdeporte.asdeportev2.ui.reusableview.inscription.PaymentType

class SelectPaymentWalletItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: SelectPaymentWalletItemViewBinding

    var selectedItem = false

    init {
        binding = SelectPaymentWalletItemViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(type: PaymentType) {

        binding.cardView.setOnClickListener {
            if (selectedItem) {
                selectedItem = false
                binding.selectedButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_off))
                binding.selectedButton.setColorFilter(ContextCompat.getColor(context, R.color.gray_600), PorterDuff.Mode.SRC_IN)
                binding.cardView.background = ContextCompat.getDrawable(context, R.drawable.gray_border_corner_3)
                //binding.toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white_dynamic))
            } else {
                selectedItem = true
                binding.selectedButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_on))
                binding.selectedButton.setColorFilter(ContextCompat.getColor(context, R.color.orange_as_light), PorterDuff.Mode.SRC_IN)
                binding.cardView.background = ContextCompat.getDrawable(context, R.drawable.kit_item_selected)
            }
        }

        when (type) {
            PaymentType.PAYPAL -> {
                binding.cardImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_paypal
                    )
                )
                binding.titlePayment.text = "PayPal"
            }
            PaymentType.STORE -> {
                binding.cardImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_store
                    )
                )
                binding.titlePayment.text = "Tiendas de Convenencia"
            }
            PaymentType.KUESKI -> {
                binding.cardImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_kueski
                    )
                )
                binding.titlePayment.text = "Kueski"
            }
            else -> {

            }
        }
    }

}