package com.asdeporte.asdeportev2.ui.access

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.LoginMenuBottomSheetBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LoginMenuBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: LoginMenuBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginMenuBottomSheetBinding.inflate(inflater)
        initListeners()
        return binding.root
    }
    private fun initListeners(){

        binding.apply {
            btnFree.setOnClickListener{
            }
            btnFreeWAccount.setOnClickListener{
            }
            btnPlus.setOnClickListener{

            }
            btnPlusWData.setOnClickListener{
                requireActivity().run {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}