package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.databinding.EventMyScoreViewBinding

class EventMyScoreView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private lateinit var binding: EventMyScoreViewBinding

    init {
        binding = EventMyScoreViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

}