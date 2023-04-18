package com.asdeporte.asdeportev2.ui.profile.adapters.badget

import androidx.annotation.DrawableRes

data class BadgeModel(
        val year: String,
        val nameShort: String,
        val state: String,
        val date: String,
        val fullName: String,
        val position: String,
        val time: String,
        val image: String ?= null,
        @DrawableRes
        val imageResource: Int ?= null,
)
