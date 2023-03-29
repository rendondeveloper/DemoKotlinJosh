package com.asdeporte.asdeportev2.ui.mitribu.subtabs.tribus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FragmentSeeEventDetailBinding
import com.asdeporte.asdeportev2.ui.mitribu.adapters.AddTribuMemberAdapter
import com.asdeporte.asdeportev2.ui.mitribu.adapters.FriendAdapter
import com.asdeporte.asdeportev2.ui.mitribu.adapters.RequestsViewPagerAdapter
import com.asdeporte.asdeportev2.ui.mitribu.subtabs.EventDetailsFragment
import com.asdeporte.asdeportev2.ui.mitribu.subtabs.EventLocationFragment
import com.asdeporte.asdeportev2.ui.mitribu.subtabs.RequestsReceivedFragment
import com.asdeporte.asdeportev2.ui.mitribu.subtabs.RequestsSentFragment
import com.google.android.material.tabs.TabLayout

class SeeEventDetailFragment : Fragment() {

    lateinit var binding: FragmentSeeEventDetailBinding
    lateinit var eventsViewPagerAdapter: EventsViewPagerAdapter
    private lateinit var friendAdapter: FriendAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeeEventDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        val testEvent = EventData(
            "123",
            "7, 14 y 21K by WomanUp",
            "https://d3cnkhyiyh0ve2.cloudfront.net/upload%2F2021%2F6%2Fimg_1625774286890_21K-WUp-logo-A-jul-6.jpg",
            "https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3285&q=80"
        )
        val items = listOf(
            testEvent,
            testEvent,
            testEvent
        )

        friendAdapter = FriendAdapter(requireContext()).apply {
            onItemClick = {
                //EventBottomSheet.create(this@TabTribuTribusFragment, it).show(requireActivity().supportFragmentManager, "EventBottomSheet")
            }
        }

        binding.rvFriends.adapter = friendAdapter
        binding.rvFriends.setHasFixedSize(true)
        binding.rvFriends.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        friendAdapter.setItems(items)

        eventsViewPagerAdapter = EventsViewPagerAdapter(requireActivity())
        binding.vpRequests.adapter = eventsViewPagerAdapter
        binding.tabView.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.vpRequests.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        binding.vpRequests.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabView.getTabAt(position)?.select()
            }
        })

    }

    class EventsViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount() = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> EventDetailsFragment()
                else -> EventLocationFragment()
            }
        }
    }
}