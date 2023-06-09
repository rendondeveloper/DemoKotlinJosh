package com.asdeporte.asdeportev2.ui.reusableview.tribu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.FilterHomePostSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterHomePostsSheet : BottomSheetDialogFragment() {

    interface FilterHomePostsSheetListener {
        fun onApplyFilters()
    }

    private lateinit var binding: FilterHomePostSheetBinding

    lateinit var listener: FilterHomePostsSheetListener

    companion object {
        fun create(listener: FilterHomePostsSheetListener) = FilterHomePostsSheet().apply {
            this.listener = listener
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FilterHomePostSheetBinding.inflate(inflater)

        return binding.root
    }


}