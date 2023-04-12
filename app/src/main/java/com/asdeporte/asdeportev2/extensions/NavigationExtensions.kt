package com.asdeporte.asdeportev2.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import timber.log.Timber

fun NavController.safelyNavigate(@IdRes resId: Int, args: Bundle? = null) =
    try { navigate(resId, args) }
    catch (e: Exception) {
        Timber.e(e)
    }