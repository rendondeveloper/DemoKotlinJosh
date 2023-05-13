package com.asdeporte.asdeportev2.ui.home

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
import com.asdeporte.asdeportev2.databinding.FragmentDetailResultsBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.subtabs.CompareFragment
import com.asdeporte.asdeportev2.ui.mitribu.subtabs.ResultsFragment
import com.google.android.material.tabs.TabLayout

class DetailResultsFragment : Fragment() {

    private lateinit var binding: FragmentDetailResultsBinding
    private lateinit var resultsViewPagerAdapter: ResultsViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()

        binding.toolbar.setOnClickListener {
            findNavController().popBackStack()
        }

        resultsViewPagerAdapter = ResultsViewPagerAdapter(requireActivity()) {
            findNavController().safelyNavigate(R.id.action_resultsDetailFragment_to_compairFriendsFragment)
        }

        initTabs()

        binding.vpResults.adapter = resultsViewPagerAdapter
        binding.tabViewOptions.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.vpResults.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        binding.vpResults.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabViewOptions.getTabAt(position)?.select()
            }
        })
    }

    private fun initTabs() {
        val titles = listOf("Resultados", "Galería", "Certificado")
        binding.tabViews.setTabs(titles)
        binding.tabViews.setCallback { tab ->
            binding.llContentResults.visibility = View.GONE
            binding.llContentGallery.visibility = View.GONE
            binding.llContentCertificate.visibility = View.GONE
            when (tab) {
                "Resultados" -> {
                    binding.llContentResults.visibility = View.VISIBLE
                }
                "Galería" -> {
                    binding.llContentGallery.visibility = View.VISIBLE
                }
                "Certificado" -> {
                    binding.llContentCertificate.visibility = View.VISIBLE
                }
            }
        }
        binding.tabViews.setFirstStep()
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