package com.asdeporte.asdeportev2.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.NotificationChatGroupBottomSheetBinding
import com.asdeporte.asdeportev2.extensions.safelyNavigate
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NotificationChatGroupBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: NotificationChatGroupBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NotificationChatGroupBottomSheetBinding.inflate(inflater)
        setupListeners()
        return binding.root
    }

    private fun setupListeners(){
        binding.lnEdit.setOnClickListener{
            this.dismiss()
            findNavController().safelyNavigate(R.id.editGroupFragment)
        }
    }

}