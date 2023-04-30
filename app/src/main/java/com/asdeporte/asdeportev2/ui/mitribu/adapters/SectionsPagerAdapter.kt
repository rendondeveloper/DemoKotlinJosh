package com.asdeporte.asdeportev2.ui.mitribu.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.asdeporte.asdeportev2.ui.mitribu.subtabs.*

class SectionsPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> {
                TabTribuWallFragment()
            }
            1 -> {
                TabTribuTribusFragment()
            }
            2 -> {
                TabTribuFriendsFragment()
            }
            3 -> {
                TabTribuPrivacyFragment()
            }
            else -> {
                TabTribuProfileFragment()
            }
        }

    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 5
    }
}