package com.asdeporte.asdeportev2.ui.home.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.BottomSheetCreateTribeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheetCreateTribe : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetCreateTribeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetCreateTribeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}