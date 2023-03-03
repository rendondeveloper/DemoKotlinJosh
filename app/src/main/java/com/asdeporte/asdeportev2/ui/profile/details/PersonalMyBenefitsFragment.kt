package com.asdeporte.asdeportev2.ui.profile.details

import android.R
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionManager
import com.asdeporte.asdeportev2.databinding.FragmentPersonalMyBenefitsBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.google.android.material.card.MaterialCardView


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

        setupData()
    }

    private fun setupData() {
        binding.eventItems.visibility = View.GONE
        binding.eventsTitle.setOnClickListener {
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
                //binding.arrowUID.setImageResource(R.drawable.dropup)
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
                //binding.arrowUID.setImageResource(R.drawable.dropdown)
            }
            click++
        }


        binding.healthItems.visibility = View.GONE
        binding.healthTitle.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.healthView)
            if (click % 2 === 0) {
                binding.healthItems.animate()
                    .alpha(1f)
                    .setDuration(100)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            binding.healthItems.visibility = View.VISIBLE
                            super.onAnimationEnd(animation)
                        }
                    })
                //binding.arrowUID.setImageResource(R.drawable.dropup)
            } else {
                binding.healthItems.animate()
                    .alpha(0f)
                    .setDuration(100)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            binding.healthItems.visibility = View.GONE
                            super.onAnimationEnd(animation)
                        }
                    })
                //binding.arrowUID.setImageResource(R.drawable.dropdown)
            }
            click++
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}