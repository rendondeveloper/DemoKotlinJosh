package com.asdeporte.asdeportev2.ui.profile.details.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.databinding.BottomSheetHistoryActivitiesBinding
import com.asdeporte.asdeportev2.databinding.BottomSheetMyScoreBinding
import com.asdeporte.asdeportev2.ui.profile.details.bottomSheet.adapter.HistoryActivitiesBottomSheetAdapter
import com.asdeporte.asdeportev2.ui.profile.details.bottomSheet.model.HistoryActivityModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyScoreBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetMyScoreBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding =  BottomSheetMyScoreBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mbAction.setOnClickListener {
            dismiss()
        }
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }
}