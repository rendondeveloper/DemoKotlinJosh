package com.asdeporte.asdeportev2.ui.mitribu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FragmentTabsFriendsProfileBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.adapters.SectionsFriendsPagerAdapter

class TabsFriendsProfileFragment : Fragment() {

    private var _binding: FragmentTabsFriendsProfileBinding? = null
    private val binding get() = _binding!!
    private var activeTab = TribuTabs.WALL
    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTabsFriendsProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding.toolbar.setNavigationOnClickListener {
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
        }
        initTabs()
    }

    private fun initTabs(){
        val sectionsPagerAdapter = SectionsFriendsPagerAdapter(requireContext(), childFragmentManager)
        viewPager = binding.viewPager
        viewPager.offscreenPageLimit = 4
        viewPager.adapter = sectionsPagerAdapter

        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                changeTabState(position)
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })

        binding.tabWall.setOnClickListener {
            changeTabState(0)
        }
        binding.tabTribus.setOnClickListener {
            changeTabState(1)
        }
        binding.tabFriends.setOnClickListener {
            changeTabState(2)
        }
        binding.tabMore.setOnClickListener {
            changeTabState(3)
        }
    }

    private fun changeTabState(position: Int) {
        viewPager.currentItem = position

        when (position) {
            0 -> {
                activeTab = TribuTabs.WALL
                binding.tabWallImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.user_profile_active))
                binding.tabTribusImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_tribu))
                binding.tabFriendsImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_friends))
            }
            1 -> {
                activeTab = TribuTabs.TRIBUS
                binding.tabWallImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.user_profile))
                binding.tabTribusImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_tribu_active))
                binding.tabFriendsImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_friends))
            }
            2 -> {
                activeTab = TribuTabs.FRIENDS
                binding.tabWallImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.user_profile))
                binding.tabTribusImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_tribu))
                binding.tabFriendsImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_friends_active))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

