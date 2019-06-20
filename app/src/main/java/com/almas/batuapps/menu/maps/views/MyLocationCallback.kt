package com.almas.batuapps.menu.maps.views

import android.util.Log
import com.almas.batuapps.App
import com.almas.batuapps.data.AppConstants
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.GoogleMap

class MyLocationCallback(private val googleMap: GoogleMap?): LocationCallback() {

    override fun onLocationResult(result: LocationResult?) {
        super.onLocationResult(result)
        val listLocation = result?.locations
        if (listLocation?.isNotEmpty()!!) {
            Log.d(AppConstants.TAG_DEBUG,"MyLocationCallback # result ${listLocation.size} location")
            App.prefHelper?.setString(AppConstants.PREFERENCE_LATITUDE, listLocation[0].latitude.toString())
            App.prefHelper?.setString(AppConstants.PREFERENCE_LONGITUDE, listLocation[0].longitude.toString())
        }
    }

}