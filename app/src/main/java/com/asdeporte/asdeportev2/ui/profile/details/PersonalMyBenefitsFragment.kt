package com.asdeporte.asdeportev2.ui.profile.details

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionManager
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FragmentPersonalMyBenefitsBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class PersonalMyBenefitsFragment : Fragment() {

    private var _binding: FragmentPersonalMyBenefitsBinding? = null
    private val binding get() = _binding!!

    var click = 0
    var clickAction = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPersonalMyBenefitsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.eventAction.layoutParams.height = 40
        binding.eventAction.layoutParams.width = 40

        binding.healthAction.layoutParams.height = 40
        binding.healthAction.layoutParams.width = 40

        binding.comunityAction.layoutParams.height = 40
        binding.comunityAction.layoutParams.width = 40

        binding.reservationTabViewItem.eventAction.layoutParams.height = 40
        binding.reservationTabViewItem.eventAction.layoutParams.width = 40

        binding.reservationTabViewItem.eventAction2.layoutParams.height = 40
        binding.reservationTabViewItem.eventAction2.layoutParams.width = 40

        binding.reservationTabViewItem.eventAction3.layoutParams.height = 40
        binding.reservationTabViewItem.eventAction3.layoutParams.width = 40

        setupData()
    }

    private fun setupData() {
        _binding?.tabView?.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        _binding?.reservationTabView?.visibility = View.GONE
                        _binding?.eventsTabView?.visibility = View.VISIBLE
                    }
                    1 -> {
                        _binding?.reservationTabView?.visibility = View.VISIBLE
                        _binding?.eventsTabView?.visibility = View.GONE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        binding.reservationTabViewItem.eventAction2.setOnClickListener{
            clickAction = !clickAction
            binding.reservationTabViewItem.dummyItems.visibility = if(clickAction) View.VISIBLE else View.GONE

            binding.reservationTabViewItem.eventAction2.layoutParams.height = 40
            binding.reservationTabViewItem.eventAction2.layoutParams.width = 40

            if(clickAction){
                binding.reservationTabViewItem.eventAction2.setImageDrawable(context?.getDrawable(R.drawable.ic_minus))
            } else {
                binding.reservationTabViewItem.eventAction2.setImageDrawable(context?.getDrawable(R.drawable.ic_add))
            }

        }

        binding.eventItems.visibility = View.GONE

        binding.eventsView.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.eventsView)
            if (click % 2 === 0) {
                binding.eventItems.animate()
                        .alpha(1f)
                        .setDuration(100)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                binding.eventItems.visibility = View.VISIBLE
                                super.onAnimationEnd(animation)
                            }
                        })
                binding.eventAction.setImageDrawable(context?.getDrawable(R.drawable.ic_minus))
                binding.eventAction.layoutParams.height = 40
                binding.eventAction.layoutParams.width = 40
            } else {
                binding.eventItems.animate()
                        .alpha(0f)
                        .setDuration(100)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                binding.eventItems.visibility = View.GONE
                                super.onAnimationEnd(animation)
                            }
                        })
                binding.eventAction.setImageDrawable(context?.getDrawable(R.drawable.ic_add))
                binding.eventAction.layoutParams.height = 40
                binding.eventAction.layoutParams.width = 40
            }
            click++
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}