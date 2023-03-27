package com.asdeporte.asdeportev2.ui.reusableview.tribu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.ModalBottomSheetMembersBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheetMembers : BottomSheetDialogFragment() {

    lateinit var binding: ModalBottomSheetMembersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ModalBottomSheetMembersBinding.inflate(inflater, container, false)
        return binding.root
    }
}