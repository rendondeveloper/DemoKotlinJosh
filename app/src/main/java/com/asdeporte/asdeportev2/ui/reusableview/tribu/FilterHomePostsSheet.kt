package com.asdeporte.asdeportev2.ui.reusableview.tribu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.FilterHomePostSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FilterHomePostSheetBinding.inflate(inflater)
        println("FilterHomePostsSheet")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("FilterHomePostsSheet")
        //binding.parentView.layoutParams.max


    }

}