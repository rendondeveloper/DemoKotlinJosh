package com.asdeporte.asdeportev2.ui.profile.details


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.BottomSheetDependentsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDependents : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetDependentsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetDependentsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDeleted.isEnabled = !TextUtils.isEmpty(binding.writeTextInput.text.toString())

        binding.btnCancel.setOnClickListener{
            this.dismiss()
        }
        binding.btnDeleted.setOnClickListener{
            this.dismiss()
        }
    }
}