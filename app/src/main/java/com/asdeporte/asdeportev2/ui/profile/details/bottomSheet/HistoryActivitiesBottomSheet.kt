package com.asdeporte.asdeportev2.ui.profile.details.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.databinding.BottomSheetHistoryActivitiesBinding
import com.asdeporte.asdeportev2.ui.profile.details.bottomSheet.adapter.HistoryActivitiesBottomSheetAdapter
import com.asdeporte.asdeportev2.ui.profile.details.bottomSheet.model.HistoryActivityModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HistoryActivitiesBottomSheet : BottomSheetDialogFragment() {

    private lateinit var historyActivitiesBottomSheetAdapter: HistoryActivitiesBottomSheetAdapter

    private lateinit var binding: BottomSheetHistoryActivitiesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BottomSheetHistoryActivitiesBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyActivitiesBottomSheetAdapter = HistoryActivitiesBottomSheetAdapter().apply {
            onItemClick = {}
        }

        binding.rvItems.adapter = historyActivitiesBottomSheetAdapter
        binding.rvItems.setHasFixedSize(true)
        binding.rvItems.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val model = HistoryActivityModel("CORRER", "31/01/2023 - 06:50 am", "00:22:18 min")
        val items = listOf(model, model, model, model, model, model)
        historyActivitiesBottomSheetAdapter.setItems(items)
    }
}