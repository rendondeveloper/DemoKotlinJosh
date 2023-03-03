package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.SheetJoinTribusFilterBinding
import com.asdeporte.asdeportev2.databinding.SheetNewPostAudienceBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.mitribu.post.NewPostAudience
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class JoinTribusFilterSheet : BottomSheetDialogFragment() {

    private var _binding: SheetJoinTribusFilterBinding? = null
    private val binding get() = _binding!!

    interface JoinTribusFilterSheetListener {
        fun onApplyFilters()
        fun onCleanFilter()
    }

    lateinit var listener: JoinTribusFilterSheetListener

    companion object {
        fun create(listener: JoinTribusFilterSheetListener) = JoinTribusFilterSheet().apply {
            this.listener = listener
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = SheetJoinTribusFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancelButton.setOnClickListener {
            listener.onCleanFilter()
            this.dismiss()
        }
        binding.applyButton.setOnClickListener {
            listener.onApplyFilters()
            this.dismiss()
        }
    }

    /*
     Listeners
     */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}