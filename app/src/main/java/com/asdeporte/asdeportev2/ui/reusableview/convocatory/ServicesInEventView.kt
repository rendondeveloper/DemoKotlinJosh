package com.asdeporte.asdeportev2.ui.reusableview.convocatory

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.databinding.ServicesInEventViewBinding

class ServicesInEventView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private lateinit var binding: ServicesInEventViewBinding

    init {
        binding = ServicesInEventViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(title: String, description: String) {
        //binding.titleView.text = title
        //binding.descriptionView.text = description

        //ServiceEventItemView
        val view1 = ServiceEventItemView(context)
        view1.setData("", "")
        binding.servicesList.addView(view1)

        val view2 = ServiceEventItemView(context)
        view2.setData("", "")
        binding.servicesList.addView(view2)

        val view3 = ServiceEventItemView(context)
        view3.setData("", "")
        binding.servicesList.addView(view3)

        val view4 = ServiceEventItemView(context)
        view4.setData("", "")
        binding.servicesList.addView(view4)
    }

}