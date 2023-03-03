package com.asdeporte.asdeportev2.ui.reusableview.inscription

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.KitChooiceItemViewBinding

class KitChoiceItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var binding: KitChooiceItemViewBinding

    private var isActive = false

    init {
        binding = KitChooiceItemViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setupToHome() {
        binding.iconCircle.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_insc_home))
        binding.titleText.text = "Entrega a domicilio"
        binding.descriptionText.text = "Agrega una dirección para que podamos hacerte entrega de tu Kit a esa ubicación"
        binding.priceText.visibility = View.VISIBLE
        binding.detailsButton.visibility = View.GONE

        binding.selectButton.setOnClickListener {
            if (isActive) {
                isActive = false
                binding.cardView.background = ContextCompat.getDrawable(context, R.drawable.gray_border_corner_3)
            } else {
                isActive = true
                binding.cardView.background = ContextCompat.getDrawable(context, R.drawable.kit_item_selected)
            }
        }
    }

    fun setupToPlace() {
        binding.iconCircle.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_insc_location))
        binding.titleText.text = "Recoger kit en la expo"
        binding.descriptionText.text = "Al seleccionar esta opción, omite el paso de entrega a domicilio y continuas para realizar el pago."
        binding.priceText.visibility = View.GONE
        binding.detailsButton.visibility = View.VISIBLE

        binding.selectButton.setOnClickListener {
            if (isActive) {
                isActive = false
                binding.cardView.background = ContextCompat.getDrawable(context, R.drawable.gray_border_corner_3)
            } else {
                isActive = true
                binding.cardView.background = ContextCompat.getDrawable(context, R.drawable.kit_item_selected)
            }
        }

        binding.selectButton.callOnClick()
        binding.selectButton.callOnClick()
    }

}
