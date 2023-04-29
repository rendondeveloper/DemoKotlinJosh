package com.asdeporte.asdeportev2.ui.reusableview.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.BlogViewBinding
import com.google.android.material.tabs.TabLayout

class BlogView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: BlogViewBinding? = null

    init {
        binding = BlogViewBinding.inflate(LayoutInflater.from(context), this, true)
        setData()
    }


    fun setData(){
        val tab1 = binding?.tabView?.getTabAt(0)
        val tab2 = binding?.tabView?.getTabAt(1)
        val tabView1 = (tab1?.view as ViewGroup).getChildAt(1) as TextView
        val tabView2 = (tab2?.view as ViewGroup).getChildAt(1) as TextView
        tabView1.setTextAppearance(R.style.SelectedTabText)
        tabView2.setTextAppearance(R.style.CustomTabGrayText)
        binding?.tabView?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    val tabView = (tab.view as ViewGroup).getChildAt(1) as TextView
                    tabView.setTextAppearance(R.style.SelectedTabText)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.let {
                    val tabView = (tab.view as ViewGroup).getChildAt(1) as TextView
                    tabView.setTextAppearance(R.style.CustomTabGrayText)
                }
            }
        })
    }
}