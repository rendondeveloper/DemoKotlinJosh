package com.asdeporte.asdeportev2.ui.profile.details.wallet

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.BottomSheetCardBinding
import com.asdeporte.asdeportev2.ui.alert.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CardBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetCardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetCardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lnCardDefault.setOnClickListener{
            val alert = AlertDialog(
                title = "Asignar como predeterminada",
                message = "Tu tarjeta con terminación **** 0456 dejará de ser tu tarjeta principal\n" +
                        "\n" +
                        "¿Estás seguro que deseas cambiar tu tarjeta predeterminada?",
                isButtonAction = true).apply {
                onConfirmClick = {
                    this.dismiss()
                }
                onCancelClick = {
                    this.dismiss()
                }
            }
            alert.show(requireActivity().supportFragmentManager, this.tag)
            this.dismiss()
        }
        binding.lnCardEdit.setOnClickListener{
            WalletNewCardSheet(true).show(requireActivity().supportFragmentManager, "PersonalWalletFragment")
            this.dismiss()
        }
        binding.lnCardDelete.setOnClickListener{
            val alert = AlertDialog(
                title = "Eliminar tarjeta",
                message = "¿Estás seguro que deseas eliminar tu tarjeta con terminación **** 0456?",
                isButtonAction = true).apply {
                onConfirmClick = {
                    this.dismiss()
                }
                onCancelClick = {
                    this.dismiss()
                }
            }
            alert.show(requireActivity().supportFragmentManager, this.tag)
            this.dismiss()
        }
    }

}