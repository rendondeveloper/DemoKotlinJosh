package com.asdeporte.asdeportev2.ui.profile.details.bottomSheet.model

import androidx.annotation.DrawableRes

data class DeviceIntegrationModel(
        val title: String,
        val description: String,
        val isConnect : Boolean,
        @DrawableRes
        val imageDevice: Int ?= null,
)
