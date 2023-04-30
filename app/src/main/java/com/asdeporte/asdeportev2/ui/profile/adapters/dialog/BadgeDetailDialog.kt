package com.asdeporte.asdeportev2.ui.profile.adapters.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.asdeporte.asdeportev2.databinding.BadgeDetailDialogBinding
import com.asdeporte.asdeportev2.ui.profile.adapters.bottomSheet.BadgeShareSheet

class BadgeDetailDialog: DialogFragment(),  BadgeShareSheet.EventBottomSheetListenerBadgeShare{

    private var _binding: BadgeDetailDialogBinding? = null

    private val binding get() = _binding!!

    var onConfirmClick: ((buttonId: Int) -> Unit)? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = BadgeDetailDialogBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setupListeners()

        return dialog
    }

    private fun setupListeners() {
        _binding?.confirmButton?.setOnClickListener {
            this.dismiss()
        }
        _binding?.imageClosed?.setOnClickListener {
            this.dismiss()
        }

        _binding?.linearLayout12?.setOnClickListener{
            BadgeShareSheet.create(this).show(requireActivity().supportFragmentManager, "EventBottomSheet")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOpenEventShare(event: String) {
    }

}