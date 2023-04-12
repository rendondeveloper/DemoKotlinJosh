package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.asdeporte.asdeportev2.databinding.FragmentMitribuRequestBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.adapters.RequestsViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class MitribuRequestFragment : Fragment() {

    lateinit var binding: FragmentMitribuRequestBinding
    lateinit var requestsViewPagerAdapter: RequestsViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMitribuRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()

        binding.toolbarRequests.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        requestsViewPagerAdapter = RequestsViewPagerAdapter(requireActivity())
        binding.vpRequests.adapter = requestsViewPagerAdapter
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
}