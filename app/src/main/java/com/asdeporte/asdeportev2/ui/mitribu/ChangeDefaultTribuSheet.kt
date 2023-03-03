package com.asdeporte.asdeportev2.ui.mitribu

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.NotificationsFilterDialogBinding
import com.asdeporte.asdeportev2.databinding.SheetChangeDefaultTribuBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChangeDefaultTribuDialog: DialogFragment() {

    private var _binding: SheetChangeDefaultTribuBinding? = null
    // This property is only valid between onCreateDialog and
    // onDestroyView.
    private val binding get() = _binding!!

    var onConfirmClick: ((buttonId: Int) -> Unit)? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = SheetChangeDefaultTribuBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.descText.text = Html.fromHtml(resources.getString(R.string.select_tribu_main_desc), Html.FROM_HTML_MODE_LEGACY)

        binding.firstTribu.setData()
        binding.secondTribu.setData()

        setupListeners()

        return dialog
    }

    private fun setupListeners() {
        binding.cancelButton.setOnClickListener {
            this.dismiss()
        }
        binding.confirmButton.setOnClickListener {
            this.dismiss()
            this.onConfirmClick?.invoke(0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}