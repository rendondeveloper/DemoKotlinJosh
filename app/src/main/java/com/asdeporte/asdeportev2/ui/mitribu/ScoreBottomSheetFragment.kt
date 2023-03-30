package com.asdeporte.asdeportev2.ui.mitribu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.ScoreBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ScoreBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: ScoreBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ScoreBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }
}