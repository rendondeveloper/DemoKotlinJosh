package com.asdeporte.asdeportev2.ui.access

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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

    private fun initListeners() {

        // Obtén una instancia de SharedPreferences
        context?.let {
            val sharedPref: SharedPreferences = it.getSharedPreferences("MiPreferencia", Context.MODE_PRIVATE)

            // Crea un editor de SharedPreferences
            val editor: SharedPreferences.Editor = sharedPref.edit()

            binding.apply {
                btnFree.setOnClickListener {
                    editor.putString("screenType", "Free")
                    editor.apply()
                    goToMainActivity()
                }
                btnFreeWAccount.setOnClickListener {
                    editor.putString("screenType", "FreeWithAccount")
                    editor.apply()
                    goToMainActivity()
                }
                btnPlus.setOnClickListener {
                    editor.putString("screenType", "PlusNoData")
                    editor.apply()
                    goToMainActivity()
                }
                btnPlusWData.setOnClickListener {
                    editor.putString("screenType", "PlusWithData")
                    editor.apply()
                    goToMainActivity()
                }
                btnPlusWDataNComp.setOnClickListener {
                    editor.putString("screenType", "PlusWithDataNextComp")
                    editor.apply()
                    goToMainActivity()
                }
            }

        }
    }

    private fun goToMainActivity() {
        requireActivity().run {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}