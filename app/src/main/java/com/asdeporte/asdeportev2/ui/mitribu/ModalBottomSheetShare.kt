package com.asdeporte.asdeportev2.ui.mitribu

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.BottomSheetShareBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheetShare : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetShareBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetShareBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.postMessageInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @SuppressLint("ResourceAsColor")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0.toString()
                if (text.isEmpty()) {
                    binding.publishButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.border_gray
                        )
                    )
                    binding.publishButton.setTextColor(ContextCompat.getColor(
                        requireContext(),
                        R.color.gray_text
                    ))
                } else {
                    binding.publishButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.orange_as_light
                        )
                    )
                    binding.publishButton.setTextColor(ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    ))
                }
            }
        })
        binding.lnCompartir.setOnClickListener {
            val compartirtc = Intent(Intent.ACTION_SEND)
            startActivity(Intent.createChooser(compartirtc, ""))
        }
    }
}