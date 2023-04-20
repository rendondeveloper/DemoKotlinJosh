package com.asdeporte.asdeportev2.ui.profile.adapters.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.BadgeFilterBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BadgeFilterSheet : BottomSheetDialogFragment() {

    interface EventBottomSheetListenerBadge {
        fun onOpenEvent(event: String)
    }

    private lateinit var binding: BadgeFilterBottomSheetBinding

    lateinit var listener: EventBottomSheetListenerBadge


    companion object {
        fun create(listener: EventBottomSheetListenerBadge) = BadgeFilterSheet().apply {
            this.listener = listener
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BadgeFilterBottomSheetBinding.inflate(inflater)
        return binding.root
    }
}