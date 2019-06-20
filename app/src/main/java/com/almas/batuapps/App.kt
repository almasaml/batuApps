package com.almas.batuapps

import android.app.Application
import android.util.Log
import com.almas.batuapps.data.AppConstants
import com.almas.batuapps.utils.PreferencesHelper
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

class App: Application() {
    companion object{
        var prefHelper: PreferencesHelper? = null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(AppConstants.TAG_DEBUG,"App # onCreate")
        prefHelper = PreferencesHelper(this)
        if (BuildConfig.FLAVOR.equals("production", true)) {
            Log.d(AppConstants.TAG_DEBUG,"App # product flavor PRODUCTION")
            Fabric.with(this, Crashlytics())
        } else {
            Log.d(AppConstants.TAG_DEBUG,"App # product flavor DEVELOPMENT")
        }
    }
}