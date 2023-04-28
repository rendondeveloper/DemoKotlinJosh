package com.asdeporte.asdeportev2.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.databinding.NotificationChatBottomSheetBinding
import com.asdeporte.asdeportev2.ui.alert.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NotificationChatBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: NotificationChatBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NotificationChatBottomSheetBinding.inflate(inflater)

        binding.lnDeletedConversation.setOnClickListener{
            val alert = AlertDialog(
                title = "Borrar conversación",
                message = "¿Seguro que deseas eliminar la conversación?",
                isButtonAction = true).apply {
                onConfirmClick = {
                    this.dismiss()
                }
                onCancelClick = {
                    this.dismiss()
                }
            }
            alert.show(requireActivity().supportFragmentManager, this.tag)
        }
        binding.lnDeletedFriend.setOnClickListener{
            val alert = AlertDialog(
                title = "Eliminar amigo",
                message = "¿Seguro que deseas eliminar amigo?",
                isButtonAction = true).apply {
                onConfirmClick = {
                    this.dismiss()
                }
                onCancelClick = {
                    this.dismiss()
                }
            }
            alert.show(requireActivity().supportFragmentManager, this.tag)
            /*
            MaterialAlertDialogBuilder(requireContext()).apply {
                setTitle("Eliminar amigo")
                setMessage("¿Seguro que deseas eliminar amigo?")
                setPositiveButton("Si, Eliminar") { _, _ -> findNavController().popBackStack() }
                setNegativeButton("Cancelar", null)
                show()
            }
             */
        }
        binding.lnBlocked.setOnClickListener{
            val alert = AlertDialog(
                title = "Bloquear",
                message = "¿Seguro que deseas bloquear a Luis Aldama?",
                isButtonAction = true).apply {
                onConfirmClick = {
                    this.dismiss()
                }
                onCancelClick = {
                    this.dismiss()
                }
            }
            alert.show(requireActivity().supportFragmentManager, this.tag)
        }
        return binding.root
    }


}