package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.TooltipCompat
import androidx.core.widget.TextViewCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.EventProgressGoalBinding

class EventProgressGoal @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: EventProgressGoalBinding? = null

    init {
        binding = EventProgressGoalBinding.inflate(LayoutInflater.from(context), this, true)

    }

    fun setData(events: String) {
        //binding.numberEvents.text = events
    }

}