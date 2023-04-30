package com.asdeporte.asdeportev2.ui.profile.adapters.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.BadgeShareBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BadgeShareSheet : BottomSheetDialogFragment() {

    interface EventBottomSheetListenerBadgeShare {
        fun onOpenEventShare(event: String)
    }

    private lateinit var binding: BadgeShareBottomSheetBinding

    lateinit var listener: EventBottomSheetListenerBadgeShare

    companion object {
        fun create(listener: EventBottomSheetListenerBadgeShare) = BadgeShareSheet().apply {
            this.listener = listener
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BadgeShareBottomSheetBinding.inflate(inflater)
        return binding.root
    }
}