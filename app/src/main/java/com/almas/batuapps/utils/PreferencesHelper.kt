package com.almas.batuapps.utils

import android.content.Context
import android.content.SharedPreferences
import com.almas.batuapps.data.AppConstants

class PreferencesHelper(context: Context) {

    private val prefHelper: SharedPreferences = context.getSharedPreferences(AppConstants.PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun setString(key: String, value: String){
        prefHelper.edit().putString(key, value).apply()
    }

    fun getString(key: String): String{
        return prefHelper.getString(key, "")
    }
}