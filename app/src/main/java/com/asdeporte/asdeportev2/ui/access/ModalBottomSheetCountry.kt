package com.asdeporte.asdeportev2.ui.access

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.asdeporte.asdeportev2.databinding.BottomSheetCountryBinding
import com.asdeporte.asdeportev2.ui.access.adapters.CountryAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheetCountry(val listener: (String) -> Unit) : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetCountryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetCountryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val countries = arrayListOf("México", "Argentina", "Chile", "Colombia")

        binding.apply {
            rvCountry.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = CountryAdapter(countries) { country ->
                    listener(country)
                }
            }
        }
    }
}