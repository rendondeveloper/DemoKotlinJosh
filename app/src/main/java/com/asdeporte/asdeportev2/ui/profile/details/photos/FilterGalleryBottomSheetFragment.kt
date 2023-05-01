package com.asdeporte.asdeportev2.ui.profile.details.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.SheetGalleryFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterGalleryBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: SheetGalleryFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SheetGalleryFilterBinding.inflate(inflater, container, false)
        setData()
        return binding.root
    }

    private fun setData(){
        binding.cancelButton.setOnClickListener{
            this.dismiss()
        }
        binding.applyButton.setOnClickListener{
            this.dismiss()
        }
    }
}