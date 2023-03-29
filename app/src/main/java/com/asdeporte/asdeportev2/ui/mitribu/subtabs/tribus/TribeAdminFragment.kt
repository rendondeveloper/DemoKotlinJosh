package com.asdeporte.asdeportev2.ui.mitribu.subtabs.tribus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.databinding.FragmentTribeAdminBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.alert.AlertDialog
import com.asdeporte.asdeportev2.ui.notifications.NotificationsFilterDialog

class TribeAdminFragment : Fragment() {

    lateinit var binding: FragmentTribeAdminBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTribeAdminBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
        }

        binding.abandomTribu.setOnClickListener {
            val alert = AlertDialog(
                title = "Abandonar tribu",
                message = "Antes de abandonar la tribu, por favor selecciona al nuevo administrador",
                isButtonAction = true).apply {
                onConfirmClick = {
                    (activity as MainActivity).onBackPressedDispatcher.onBackPressed()
                }
            }
            alert.show(requireActivity().supportFragmentManager, this.tag)
        }

        binding.stateTextInput.setOnClickListener {
            val alert = AlertDialog(
                title = "Nuevo administrador",
                message = "¿Estás seguro de ceder la administración a Alicia Martínez?",
                isButtonAction = true).apply {
                onConfirmClick = {
                    findNavController().popBackStack()
                }
            }
            alert.show(requireActivity().supportFragmentManager, this.tag)
        }

        binding.deleteTribu.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}