package com.asdeporte.asdeportev2.ui.mitribu.subtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asdeporte.asdeportev2.databinding.FragmentJoinTribuBinding
import com.asdeporte.asdeportev2.databinding.FragmentMiTribuRequestsBinding
import com.asdeporte.asdeportev2.ui.MainActivity
import com.asdeporte.asdeportev2.ui.alert.AlertDialog
import com.asdeporte.asdeportev2.ui.mitribu.adapters.SmallTribuJoinAdapter
import com.asdeporte.asdeportev2.ui.mitribu.adapters.TribuUserRequestAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class JoinTribuFragment : Fragment() {
    private var _binding: FragmentJoinTribuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentJoinTribuBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as MainActivity).hideActionBar()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setOnClickListener {
            findNavController().popBackStack()
        }

        //binding.filterButton.setOnClickListener {
            //JoinTribusFilterSheet.create(this@JoinTribuFragment).show(requireActivity().supportFragmentManager, "MiTribuRequestsFragment")
        //}

        binding.notNowButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.joinButton.setOnClickListener {
            val alert = AlertDialog(
                title = "Unirme a grupo",
                message = "Se te notificará cuando el administrador del grupo acepte tu solicitud").apply {
                onCancelClick = {
                    findNavController().popBackStack()
                }
            }
            alert.show(requireActivity().supportFragmentManager, this.tag)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}