package com.asdeporte.asdeportev2.ui.notifications

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.asdeporte.asdeportev2.databinding.NotificationsFilterDialogBinding

class NotificationsFilterDialog: DialogFragment() {

    private var _binding: NotificationsFilterDialogBinding? = null
    // This property is only valid between onCreateDialog and
    // onDestroyView.
    private val binding get() = _binding!!

    var onConfirmClick: ((buttonId: Int) -> Unit)? = null
    var onCancelClick: ((buttonId: Int) -> Unit)? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = NotificationsFilterDialogBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setupListeners()

        return dialog
    }

    fun setupListeners() {
        _binding?.cancelButton?.setOnClickListener {
            this.dismiss()
        }
        _binding?.confirmButton?.setOnClickListener {
            this.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}