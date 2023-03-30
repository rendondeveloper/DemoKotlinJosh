package com.asdeporte.asdeportev2.ui.mitribu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FragmentMiTribuBinding
import com.asdeporte.asdeportev2.ui.mitribu.adapters.SectionsPagerAdapter

class MiTribuFragment : Fragment() {
    private var _binding: FragmentMiTribuBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var activeTab = TribuTabs.WALL
    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMiTribuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabs()
    }

    private fun initTabs() {

        val sectionsPagerAdapter = SectionsPagerAdapter(requireContext(), childFragmentManager)
        viewPager = binding.viewPager
        viewPager.offscreenPageLimit = 4 // not more scroll lag!
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
        binding.tabPrivacy.setOnClickListener {
            changeTabState(3)
        }
        binding.tabProfile.setOnClickListener {
            changeTabState(4)
        }
    }

    private fun changeTabState(position: Int) {
        viewPager.currentItem = position

        when (position) {
            0 -> {
                activeTab = TribuTabs.WALL
                binding.tabWallImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_wall_active))
                binding.tabTribusImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_tribu))
                binding.tabFriendsImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_friends))
                binding.tabPrivacyImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_privacy))
                binding.tabProfileImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.edit_profile))
            }
            1 -> {
                activeTab = TribuTabs.TRIBUS
                binding.tabWallImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_wall))
                binding.tabTribusImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_tribu_active))
                binding.tabFriendsImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_friends))
                binding.tabPrivacyImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_privacy))
                binding.tabProfileImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.edit_profile))
            }
            2 -> {
                activeTab = TribuTabs.FRIENDS
                binding.tabWallImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_wall))
                binding.tabTribusImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_tribu))
                binding.tabFriendsImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_friends_active))
                binding.tabPrivacyImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_privacy))
                binding.tabProfileImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.edit_profile))
            }
            3 -> {
                activeTab = TribuTabs.PRIVACY
                binding.tabWallImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_wall))
                binding.tabTribusImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_tribu))
                binding.tabFriendsImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_friends))
                binding.tabPrivacyImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_privacy_active))
                binding.tabProfileImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.edit_profile))
            }
            4 -> {
                activeTab = TribuTabs.EDIT_PROFILE
                binding.tabWallImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_wall))
                binding.tabTribusImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_tribu))
                binding.tabFriendsImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_friends))
                binding.tabPrivacyImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tribu_tab_privacy))
                binding.tabProfileImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.edit_profile_active))
            }
        }
    }

    /*
     Listeners
     */


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

enum class TribuTabs {
    WALL,
    TRIBUS,
    FRIENDS,
    PRIVACY,
    EDIT_PROFILE,
    MORE,
}
