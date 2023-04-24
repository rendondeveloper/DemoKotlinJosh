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


class PersonalMyBenefitsFragment : Fragment() {

    private var _binding: FragmentPersonalMyBenefitsBinding? = null
    private val binding get() = _binding!!

    var click = 0

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

        binding.eventAction.layoutParams.height = 80
        binding.eventAction.layoutParams.width = 80

        binding.healthAction.layoutParams.height = 80
        binding.healthAction.layoutParams.width = 80

        binding.comunityAction.layoutParams.height = 80
        binding.comunityAction.layoutParams.width = 80

        setupData()
    }

    private fun setupData() {
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
                binding.eventAction.layoutParams.height = 80
                binding.eventAction.layoutParams.width = 80
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
                binding.eventAction.layoutParams.height = 80
                binding.eventAction.layoutParams.width = 80
            }
            click++
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}