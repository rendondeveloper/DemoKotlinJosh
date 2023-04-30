package com.asdeporte.asdeportev2.ui.profile.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.BadgePagerFragmentBinding
import com.asdeporte.asdeportev2.ui.profile.adapters.bottomSheet.BadgeShareSheet
import com.asdeporte.asdeportev2.ui.profile.adapters.collections.BadgePagerCollectionAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator


class PersonalBadgePagerFragment : Fragment(), BadgeShareSheet.EventBottomSheetListenerBadgeShare {

    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var viewPager: ViewPager2
    private var demoCollectionAdapter: BadgePagerCollectionAdapter? = null

    private lateinit var _binding: BadgePagerFragmentBinding

    var onConfirmClick: ((buttonId: Int) -> Unit)? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = BadgePagerFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        setupListeners()
    }

    private fun setupListeners() {
        _binding.imageClosed.setOnClickListener {
            findNavController().popBackStack()
        }

        demoCollectionAdapter = BadgePagerCollectionAdapter(this)

        _binding.let {
            viewPager = it.pagerBadge
            viewPager.adapter = demoCollectionAdapter
            dotsIndicator = it.dotsIndicator
            dotsIndicator.selectedDotColor = ContextCompat.getColor(requireContext(), R.color.orange_as_light)
            dotsIndicator.attachTo(viewPager)
        }
    }

    override fun onOpenEventShare(event: String) {
    }

}