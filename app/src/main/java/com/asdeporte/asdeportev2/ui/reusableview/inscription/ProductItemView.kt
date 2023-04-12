package com.asdeporte.asdeportev2.ui.reusableview.inscription

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.ProductItemViewBinding
import com.bumptech.glide.Glide

class ProductItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var binding: ProductItemViewBinding

    private var isAdded = false

    init {
        binding = ProductItemViewBinding.inflate(LayoutInflater.from(context), this, true)
        setupAction()
    }

    private fun setupAction() {

        binding.addButton.setOnClickListener {
            if (isAdded) {
                isAdded = false
                binding.cardView.background = ContextCompat.getDrawable(context, R.drawable.gray_border_corner_3)
                binding.addButton.background = ContextCompat.getDrawable(context, R.drawable.secondary_button)
                binding.addButton.setTextColor(ContextCompat.getColor(context, R.color.black_dynamic))
                binding.addButton.text = "Agregar"
            } else {
                isAdded = true
                binding.cardView.background = ContextCompat.getDrawable(context, R.drawable.kit_item_selected)
                binding.addButton.background = ContextCompat.getDrawable(context, R.drawable.pill_background)
                binding.addButton.setTextColor(ContextCompat.getColor(context, R.color.white_dynamic))
                binding.addButton.text = "Quitar"

            }
        }

    }

    fun setAsProduct() {
        Glide.with(this)
            .load("https://cdn.accentuate.io/6671152218189/1652716714793/3_PNW_Water_Bottle_Orange.png?v=1652734582805")
            .centerInside()
            .into(binding.productImg)

        binding.titleText.text = "Recoger kit en la expo"
    }
    fun setAsTribike() {
        Glide.with(this)
            .load("https://www.endurancesportswire.com/wp-content/uploads/2011/04/tbt-logosArtboard-1.png")
            .centerInside()
            .into(binding.productImg)

        binding.titleText.text = "Tribike Transport"
        binding.descriptionText.visibility = View.GONE
        binding.priceText.text = "\$ 687.00 MXN"
        binding.infoText.text = "Para deportistas con grandes beneficios en eventos, experiencias, contenidos y promociones. "
    }

}
