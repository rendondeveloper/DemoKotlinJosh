package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.PrivacyMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PrivacyMenuBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: PrivacyMenuBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PrivacyMenuBottomSheetBinding.inflate(inflater)
        return binding.root
    }
}