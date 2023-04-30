package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.databinding.SheetAdminTribuBinding
import com.asdeporte.asdeportev2.ui.alert.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AdminTribuSheet : BottomSheetDialogFragment() {

    private var _binding: SheetAdminTribuBinding? = null
    private val binding get() = _binding!!

    interface AdminTribuSheetListener {
        fun onChangeAdmin()
        fun onAbandom()
        fun onDelete()
    }

    lateinit var listener: AdminTribuSheetListener

    companion object {
        fun create(listener: AdminTribuSheetListener) = AdminTribuSheet().apply {
            this.listener = listener
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = SheetAdminTribuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.changeAdmin.setOnClickListener {
            listener.onChangeAdmin()
            this.dismiss()
        }

        binding.abandomTribu.setOnClickListener {
            val alert = AlertDialog(
                title = "Abandonar tribu",
                message = "Antes de abandonar la tribu, por favor selecciona al nuevo administrador",
                isButtonAction = true).apply {
                onConfirmClick = {
                    findNavController().popBackStack()
                }
                onCancelClick = {
                    this.dismiss()
                }
            }
            alert.show(requireActivity().supportFragmentManager, this.tag)
        }

        binding.deleteTribu.setOnClickListener {
            listener.onDelete()
            this.dismiss()
        }
    }

    /*
     Listeners
     */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}