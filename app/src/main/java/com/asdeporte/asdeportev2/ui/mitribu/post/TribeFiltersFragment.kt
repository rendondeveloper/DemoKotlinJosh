package com.asdeporte.asdeportev2.ui.mitribu.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.databinding.FragmentTribeFiltersBinding
import com.asdeporte.asdeportev2.ui.MainActivity

class TribeFiltersFragment : Fragment() {

    lateinit var binding: FragmentTribeFiltersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTribeFiltersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideActionBar()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.confirmButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}