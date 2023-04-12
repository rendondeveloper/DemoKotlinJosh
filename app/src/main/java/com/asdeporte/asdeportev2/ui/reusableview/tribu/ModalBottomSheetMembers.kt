package com.asdeporte.asdeportev2.ui.reusableview.tribu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.ModalBottomSheetMembersBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheetMembers(private val type: String = "share") : BottomSheetDialogFragment() {

    lateinit var binding: ModalBottomSheetMembersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ModalBottomSheetMembersBinding.inflate(inflater, container, false)
        initListeners()
        return binding.root
    }

    private fun initListeners(){
        if(type == "add"){
            binding.lnAdd.visibility = VISIBLE
        }
        binding.lnShare.setOnClickListener{
            val iShare = Intent(Intent.ACTION_SEND)
            startActivity(Intent.createChooser(iShare, ""))
        }
    }
}