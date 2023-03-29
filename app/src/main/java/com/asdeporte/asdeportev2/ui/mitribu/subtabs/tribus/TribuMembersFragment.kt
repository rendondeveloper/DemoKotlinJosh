package com.asdeporte.asdeportev2.ui.mitribu.subtabs.tribus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentTribuMembersBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.adapters.TribuResultAdapter
import com.asdeporte.asdeportev2.ui.reusableview.tribu.FriendDefaultView
import com.asdeporte.asdeportev2.ui.reusableview.tribu.FriendMenuOption
import com.google.android.material.tabs.TabLayout

class TribuMembersFragment : Fragment(), FriendDefaultView.FriendDefaultViewListener {

    private var _binding: FragmentTribuMembersBinding? = null
    private val binding get() = _binding!!

    private lateinit var resultsdapter: TribuResultAdapter
    lateinit var membersViewPagerAdapter: MembersViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTribuMembersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding.toolbar.setNavigationOnClickListener {
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
        }

        setupData()

    }

    private fun setupData() {
        membersViewPagerAdapter = MembersViewPagerAdapter(requireActivity())
        binding.vpMembers.adapter = membersViewPagerAdapter
        binding.tabView.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.vpMembers.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        binding.vpMembers.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabView.getTabAt(position)?.select()
            }
        })
    }

    /*
     Listeners
     */
    override fun onOptionSelected(friendId: Int, option: FriendMenuOption) {
        //TODO("Not yet implemented")
    }

    class MembersViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount() = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MembersFragment(false)
                else -> MembersFragment(true)
            }
        }
    }
}