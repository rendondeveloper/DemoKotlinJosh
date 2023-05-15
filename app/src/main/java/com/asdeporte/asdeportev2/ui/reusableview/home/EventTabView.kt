package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.asdeporte.asdeportev2.R
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.allViews
import com.asdeporte.asdeportev2.databinding.EventTabViewBinding


class EventTabView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: EventTabViewBinding
    var currentTab = 0

    lateinit var callBack: (String) -> Unit

    init {
        binding = EventTabViewBinding.inflate(LayoutInflater.from(context), this, true)
    }


    fun setCallback(callBack: (String) -> Unit) {
        this.callBack = callBack
    }

    fun setTabs(titles: List<String>) {
        //binding.numberEvents.text = events

        binding.tabView.removeAllViews()

        for ((index, title) in titles.withIndex()) {
            val inflater = LayoutInflater.from(context)
            val layout = inflater.inflate(R.layout.event_single_tab_view, null, false) as ConstraintLayout
            val tabContainer: RelativeLayout = layout.findViewById(R.id.tab_container) as RelativeLayout
            tabContainer.tag = index
            val titleView: TextView = layout.findViewById(R.id.title_view) as TextView
            titleView.text = title

            tabContainer.setOnClickListener {
                currentTab = it.tag as Int
                removeSelected()
                tabContainer.background = resources.getDrawable(R.drawable.event_tab_border_select)
                titleView.setTextColor(ContextCompat.getColor(context, R.color.white))
                this.callBack(titleView.text.toString())
            }

            binding.tabView.addView(layout)
        }
    }

    fun setFirstStep() {
        currentTab = 0
        val view =  binding.tabView.allViews.firstOrNull()

        view?.let {
            val tabContainer = it.findViewById(R.id.tab_container) as? RelativeLayout
            val titleView = it.findViewById(R.id.title_view) as? TextView
            tabContainer?.background = resources.getDrawable(R.drawable.event_tab_border_select)
            titleView?.setTextColor(ContextCompat.getColor(context, R.color.white))
            this.callBack(titleView?.text.toString())
        }
    }

    private fun removeSelected() {
        for (view in binding.tabView.allViews) {
            val tabContainer = view.findViewById(R.id.tab_container) as? RelativeLayout
            val titleView = view.findViewById(R.id.title_view) as? TextView
            tabContainer?.background = resources.getDrawable(R.drawable.event_tab_border)
            titleView?.setTextColor(ContextCompat.getColor(context, R.color.dark_blue))
        }
    }

}