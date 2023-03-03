package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.asdeporte.asdeportev2.R
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.databinding.EventTabViewBinding


class EventTabView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private lateinit var binding: EventTabViewBinding

    private var currentTab = 0

    init {
        binding = EventTabViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setTabs(titles: List<String>) {
        //binding.numberEvents.text = events

        binding.tabView.removeAllViews()

        for ((index, title) in titles.withIndex()) {
            val inflater = LayoutInflater.from(context)
            val layout = inflater.inflate(R.layout.event_single_tab_view, null, false) as ConstraintLayout

            val tabContainer: RelativeLayout = layout.findViewById(R.id.tab_container) as RelativeLayout
            val titleView: TextView = layout.findViewById(R.id.title_view) as TextView
            titleView.text = title

            if (currentTab == index) {
                tabContainer.backgroundTintList = ContextCompat.getColorStateList(context, R.color.dark_blue)
                titleView.setTextColor(ContextCompat.getColor(context, R.color.white))
            }

            layout.setOnClickListener {
                //if (currentTab)
                tabContainer.backgroundTintList = ContextCompat.getColorStateList(context, R.color.dark_blue)
                titleView.setTextColor(ContextCompat.getColor(context, R.color.white))
            }

            binding.tabView.addView(layout)
        }

    }

}