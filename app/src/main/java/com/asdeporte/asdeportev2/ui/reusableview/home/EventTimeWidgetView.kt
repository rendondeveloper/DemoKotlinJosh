package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.EventTimeWidgetViewBinding

class EventTimeWidgetView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: EventTimeWidgetViewBinding? = null

    init {
        binding = EventTimeWidgetViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(events: String) {
        //binding.numberEvents.text = events
    }

}