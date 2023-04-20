package com.asdeporte.asdeportev2.ui.reusableview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.PartnersGridViewBinding


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
        //binding.numberEvents.text = events
        this.listener = listener

    }

}