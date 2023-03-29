package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.databinding.PlansViewBinding

class PlansView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private lateinit var binding: PlansViewBinding

    init {
        binding = PlansViewBinding.inflate(LayoutInflater.from(context), this, true)
    }


    fun setTitle(title: String) {

    }
}