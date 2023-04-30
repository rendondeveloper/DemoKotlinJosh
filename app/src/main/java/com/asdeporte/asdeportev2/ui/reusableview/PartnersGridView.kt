package com.asdeporte.asdeportev2.ui.reusableview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.PartnersGridViewBinding
import com.bumptech.glide.Glide

class PartnersGridView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    interface PartnersGridViewListener {
        fun onItemSelected()
    }
    private lateinit var listener: PartnersGridViewListener

    private var binding: PartnersGridViewBinding? = null

    init {
        binding = PartnersGridViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(listener: PartnersGridViewListener) {
        this.listener = listener
        binding?.apply {
            Glide.with(context)
                .load(R.drawable.partner_epura)
                .centerInside()
                .into(socio1)
            Glide.with(context)
                .load(R.drawable.partner_aeromexico)
                .centerInside()
                .into(socio2)
            Glide.with(context)
                .load(R.drawable.partner_santander)
                .centerInside()
                .into(socio3)
            Glide.with(context)
                .load(R.drawable.partner_gatorade)
                .centerInside()
                .into(socio4)
            Glide.with(context)
                .load(R.drawable.partner_gmc)
                .centerInside()
                .into(socio5)
            Glide.with(context)
                .load(R.drawable.partner_innova)
                .centerInside()
                .into(socio6)
        }

    }

}