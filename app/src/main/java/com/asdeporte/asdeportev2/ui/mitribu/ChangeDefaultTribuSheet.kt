package com.asdeporte.asdeportev2.ui.mitribu

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.SheetChangeDefaultTribuBinding

class ChangeDefaultTribuDialog : DialogFragment() {

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

        binding.descText.text = Html.fromHtml(
            resources.getString(R.string.select_tribu_main_desc),
            Html.FROM_HTML_MODE_LEGACY
        )

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
        binding.firstTribu.isSelected.observe(requireActivity()) {
            if (it)
                binding.confirmButton.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.orange_as_light
                    )
                )
            else {
                if (binding.secondTribu.isSelected.value == false) {
                    binding.confirmButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.border_gray
                        )
                    )
                }
            }
        }

        binding.secondTribu.isSelected.observe(requireActivity()) {
            if (it)
                binding.confirmButton.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.orange_as_light
                    )
                )
            else {
                if (binding.firstTribu.isSelected.value == false) {
                    binding.confirmButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.border_gray
                        )
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}