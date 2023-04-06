package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.FragmentEventFiltersBinding

class EventFiltersFragment : Fragment() {

    lateinit var binding: FragmentEventFiltersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventFiltersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.allCheck.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.allCheck.trackTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.orange_as_light))
            } else {
                binding.allCheck.trackTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.label_secondary))
            }
        }
    }
}