package com.asdeporte.asdeportev2.ui.mitribu.subtabs.tribus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FragmentResultsDetailBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.mitribu.subtabs.CompareFragment
import com.asdeporte.asdeportev2.ui.mitribu.subtabs.ResultsFragment
import com.google.android.material.tabs.TabLayout

class ResultsDetailFragment : Fragment() {

    private lateinit var binding: FragmentResultsDetailBinding
    private lateinit var resultsViewPagerAdapter: ResultsViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setOnClickListener {
            findNavController().popBackStack()
        }

        resultsViewPagerAdapter = ResultsViewPagerAdapter(requireActivity()) {
            findNavController().safelyNavigate(R.id.action_resultsDetailFragment_to_compairFriendsFragment)
        }
        binding.vpResults.adapter = resultsViewPagerAdapter
        binding.tabView.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.vpResults.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        binding.vpResults.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabView.getTabAt(position)?.select()
            }
        })
    }

    class ResultsViewPagerAdapter(fragmentActivity: FragmentActivity, val listener: () -> Unit) :
        FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount() = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> ResultsFragment()
                else -> CompareFragment {
                    listener()
                }
            }
        }
    }
}