package com.asdeporte.asdeportev2.ui.alert

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.DialogFragment
import com.asdeporte.asdeportev2.databinding.AlertDialogBinding

class AlertDialog(
    private val title: String,
    private val message: String,
    private val isButtonAction: Boolean = false
): DialogFragment() {
    private var _binding: AlertDialogBinding? = null
    private val binding get() = _binding!!
    var onConfirmClick: ((buttonId: Int) -> Unit)? = null
    var onCancelClick: ((buttonId: Int) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = AlertDialogBinding.inflate(layoutInflater)
        _binding?.txtTitle?.text = title
        _binding?.txtMessage?.text = message
        _binding?.imgClose?.visibility = if (isButtonAction) GONE else VISIBLE
        _binding?.lnActions?.visibility = if (isButtonAction) VISIBLE else GONE
        val dialog = AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setupListeners()
        return dialog
    }

    private fun setupListeners() {
        _binding?.imgClose?.setOnClickListener {
            this.dismiss()
        }
        _binding?.cancelButton?.setOnClickListener {
            this.onCancelClick?.invoke(0)
            this.dismiss()
        }
        _binding?.confirmButton?.setOnClickListener {
            this.onConfirmClick?.invoke(1)
            this.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}