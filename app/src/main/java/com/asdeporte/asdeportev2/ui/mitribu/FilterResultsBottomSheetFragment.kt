package com.asdeporte.asdeportev2.ui.mitribu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.FilterResultsBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterResultsBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: FilterResultsBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FilterResultsBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }
}