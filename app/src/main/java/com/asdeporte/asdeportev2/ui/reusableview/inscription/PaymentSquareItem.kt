package com.asdeporte.asdeportev2.ui.reusableview.inscription

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.PaymentSquareItemBinding

class PaymentSquareItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var binding: PaymentSquareItemBinding

    private var isActive = false

    init {
        binding = PaymentSquareItemBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setupPayment(type: PaymentType) {

        binding.cardView.setOnClickListener {
            if (isActive) {
                isActive = false
                binding.cardView.background = ContextCompat.getDrawable(context, R.drawable.gray_border_corner_3)
            } else {
                isActive = true
                binding.cardView.background = ContextCompat.getDrawable(context, R.drawable.kit_item_selected)

            }
        }

        when (type) {
            PaymentType.PAYPAL -> {
                binding.paymentImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_paypal))
                binding.paymentTitle.text = "PayPal"
            }
            PaymentType.STORE -> {
                binding.paymentImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_store))
                binding.paymentTitle.text = "Tiendas de Convenencia"
            }
            PaymentType.KUESKI -> {
                //Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.sample_drawable_image);
                //val kueski = BitmapFactory.decodeResource(resources, R.mipmap.ic_kueski)
                //binding.paymentImage.setImageBitmap(kueski)
                //binding.paymentImage.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.ic_kueski))
                binding.paymentImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_kueski))
                binding.paymentTitle.text = "Kueski"
            }
            PaymentType.SPEI -> {
                binding.paymentImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_spei))
                binding.paymentTitle.text = "Transferencia Bancaria"
            }
            else -> {

            }
        }
    }


}

enum class PaymentType {
    PAYPAL,
    STORE,
    KUESKI,
    SPEI
}