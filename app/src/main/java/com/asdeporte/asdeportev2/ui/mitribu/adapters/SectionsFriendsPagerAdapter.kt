package com.asdeporte.asdeportev2.ui.mitribu.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.asdeporte.asdeportev2.ui.mitribu.TribuProfileFragment
import com.asdeporte.asdeportev2.ui.mitribu.subtabs.*

class SectionsFriendsPagerAdapter(
    fm: FragmentManager,
    val listener: () -> Unit
): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> {
                TribuProfileFragment {
                    listener()
                }
            }
            1 -> {
                TabTribuTribusFragment(flow = "friends")
            }
            else -> {
                TabTribuFriendsFragment(flow = "friends")
            }
        }

    }

    override fun getCount(): Int {
        return 3
    }
}