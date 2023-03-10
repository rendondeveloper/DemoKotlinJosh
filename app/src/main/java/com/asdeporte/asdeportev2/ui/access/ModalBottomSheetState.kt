package com.asdeporte.asdeportev2.ui.access

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.databinding.BottomSheetStateBinding
import com.asdeporte.asdeportev2.ui.access.adapters.StateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheetState(val listener: (String) -> Unit) : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetStateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetStateBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val states = arrayListOf("Aguascalientes", "Chihuahua", "Ciudad de México", "Colima")

        binding.apply {
            rvState.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = StateAdapter(states) { state ->
                    listener(state)
                }
            }
        }
    }
}