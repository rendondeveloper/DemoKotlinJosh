package com.asdeporte.asdeportev2.ui.mitribu.subtabs.tribus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.FragmentTribuDetailsBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.extensions.safelyNavigate

class TribuDetailsFragment : Fragment() {

    private var _binding: FragmentTribuDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTribuDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding.toolbar.setNavigationOnClickListener {
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
        }

        binding.infoView.setOnClickListener {
            findNavController().safelyNavigate(R.id.editTribuAction)
        }
        binding.membersView.setOnClickListener {
            findNavController().safelyNavigate(R.id.membersTribuAction)
        }
        binding.eventsView.setOnClickListener {
            findNavController().safelyNavigate(R.id.eventsTribuAction)
        }
        binding.resultsView.setOnClickListener {
            findNavController().safelyNavigate(R.id.resultsTribuAction)
        }

    }


}