package com.asdeporte.asdeportev2.ui.profile.details.bottomSheet.model

import androidx.annotation.DrawableRes

data class EventHistoryModel(
        val title: String,
        val date: String,
        val category :  String,
        val league :  String,
        val detailGoal: String,
        val detailPosition: String,
        val detailTime: String,
        val detailTimeTotal: String,
        val listLaps: List<EventHistoryLapModel>,
        val listImage: List<EventHistoryImageModel>,
        @DrawableRes
        val image: Int ?= null,
)
