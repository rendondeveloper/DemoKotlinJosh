package com.asdeporte.asdeportev2.ui.mitribu.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.asdeporte.asdeportev2.ui.mitribu.subtabs.RequestsReceivedFragment
import com.asdeporte.asdeportev2.ui.mitribu.subtabs.RequestsSentFragment

class RequestsViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RequestsSentFragment()
            else -> RequestsReceivedFragment()
        }
    }
}