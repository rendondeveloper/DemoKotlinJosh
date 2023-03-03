package com.asdeporte.asdeportev2.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object SharedPreferencesAsd {

    private var preferences: SharedPreferences? = null

    // User id
    val asdUserIdKey = "asdUserIdKey"
    fun saveUserId(context: Context, userid: String?) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
        preferences?.edit()?.putString(asdUserIdKey, userid)?.apply()
    }

    fun getUserId(context: Context): String? {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences?.getString(asdUserIdKey, "")
    }

}