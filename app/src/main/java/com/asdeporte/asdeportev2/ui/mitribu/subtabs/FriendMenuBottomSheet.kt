package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.FriendMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FriendMenuBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: FriendMenuBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FriendMenuBottomSheetBinding.inflate(inflater)
        return binding.root
    }
}