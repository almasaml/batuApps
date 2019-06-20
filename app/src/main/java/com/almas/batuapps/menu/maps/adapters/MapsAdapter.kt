package com.almas.batuapps.menu.maps.adapters

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.almas.batuapps.R
import com.almas.batuapps.menu.maps.models.MarkerTag
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.fragment_maps.view.*

class MapsAdapter(activity: FragmentActivity?) : GoogleMap.InfoWindowAdapter {
    private val mContents: View = LayoutInflater.from(activity?.applicationContext).inflate(R.layout.fragment_maps, null)

    override fun getInfoContents(marker: Marker?): View {
        return mContents
    }

    override fun getInfoWindow(marker: Marker?): View {
        val tag: MarkerTag = marker?.tag as MarkerTag
        if (tag.type==0) {
            mContents.textViewMapLocationName.text = tag.title
        }
        return mContents
    }
}