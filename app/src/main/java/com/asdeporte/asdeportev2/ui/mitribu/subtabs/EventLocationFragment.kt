package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asdeporte.asdeportev2.databinding.FragmentEventLocationBinding

class EventLocationFragment : Fragment() {

    lateinit var binding: FragmentEventLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventLocationBinding.inflate(inflater, container, false)
        return binding.root
    }
}