package com.almas.batuapps.menu.maps.views

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import android.util.TypedValue
import android.view.*
import androidx.fragment.app.Fragment
import com.akexorcist.googledirection.DirectionCallback
import com.akexorcist.googledirection.GoogleDirection
import com.akexorcist.googledirection.constant.AvoidType
import com.akexorcist.googledirection.constant.TransportMode
import com.akexorcist.googledirection.model.Direction
import com.akexorcist.googledirection.model.Route
import com.akexorcist.googledirection.util.DirectionConverter
import com.almas.batuapps.App
import com.almas.batuapps.R
import com.almas.batuapps.data.AppConstants
import com.almas.batuapps.menu.maps.adapters.MapsAdapter
import com.almas.batuapps.menu.maps.models.MarkerTag
import com.almas.batuapps.menu.maps.models.PinModel
import com.almas.batuapps.networks.ServiceFactory
import com.almas.batuapps.utils.AppHelper
import com.almas.batuapps.utils.BounceAnimation
import com.almas.batuapps.widgets.AppDialog
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FragmentMaps: SupportMapFragment(), OnMapReadyCallback, DirectionCallback {
    companion object {
        const val REQUEST_FEATURE_LOCATION_PERMISSION_CODE:Int = 12
        fun getInstance(): Fragment {
            return FragmentMaps()
        }
    }

    private val restApi = ServiceFactory.create()
    private val compositeDisposable = CompositeDisposable()

    private var markerLocation: Marker? = null
    private var mGoogleMap: GoogleMap? = null
    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null
    private var myLocationCallback: MyLocationCallback? = null

    private var origin: LatLng? = null
    private var destination: LatLng? = null
    private val colors = arrayListOf("#7F2196f3","#7F4CAF50","#7FF44336")
    private lateinit var progressDialog: AppDialog

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context!!)
        setHasOptionsMenu(true)
        progressDialog = AppDialog(context!!)
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMapAsync(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        if (myLocationCallback!=null) {
            LocationServices.getFusedLocationProviderClient(context!!).removeLocationUpdates(myLocationCallback)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== REQUEST_FEATURE_LOCATION_PERMISSION_CODE) {
            if (resultCode==Activity.RESULT_OK) {
               requestPermission(mGoogleMap)
            } else if (resultCode==Activity.RESULT_CANCELED) {
                AppHelper.displayToastError(context!!, getString(R.string.location_setting_off))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_maps, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId==R.id.menuMapRefresh){
            getMapPins(mGoogleMap)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getMapPins(googleMap: GoogleMap?){
        googleMap?.clear()
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(LatLng(-7.982914, 112.630875)))
        googleMap?.animateCamera(CameraUpdateFactory.zoomTo(9F))
        compositeDisposable.add(
            restApi.getMapPins()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setupPin(googleMap, it)
                },{
                    AppHelper.displayToastError(context?.applicationContext!!, getString(R.string.error_get_data_map_pins))
                })
        )
    }

    private fun setupPin(googleMap: GoogleMap?, listPin: MutableList<PinModel>){
        if (listPin.isNotEmpty()) {
            for (pin in listPin){
                val markerOptions = MarkerOptions()
                markerOptions.position(LatLng(pin.latitude!!, pin.longitude!!))
                markerOptions.title(pin.name)
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_pin))
                markerLocation =  googleMap?.addMarker(markerOptions)
                markerLocation?.tag = MarkerTag(pin.name!!, 0, pin.latitude, pin.longitude)

                googleMap?.setInfoWindowAdapter(MapsAdapter(activity))
            }
        }
    }

    private fun setMarkerAnimation(googleMap: GoogleMap?){
        googleMap?.setOnMarkerClickListener { marker ->
            val tag: MarkerTag = marker.tag as MarkerTag
            if (tag.type==0) {
                val handler = Handler()
                val anim = BounceAnimation(SystemClock.uptimeMillis(), 1000L, marker, handler)
                handler.post(anim)
                marker.showInfoWindow()
            } else {
                marker.hideInfoWindow()
            }
            return@setOnMarkerClickListener true
        }
    }

    private fun requestPermission(googleMap: GoogleMap?){
        val rxPermission = RxPermissions(this)
        compositeDisposable.add(
            rxPermission.request(android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe{ result: Boolean ->
                    if (result) {
                        enableUserLocation(googleMap)
                    } else {
                        AppHelper.displayToastError(context?.applicationContext!!, getString(R.string.access_location_denied))
                    }
                }
        )
    }


    @SuppressLint("MissingPermission")
    private fun enableUserLocation(googleMap: GoogleMap?){
        myLocationCallback = MyLocationCallback(googleMap)
        googleMap?.isMyLocationEnabled = true
        googleMap?.uiSettings?.isMyLocationButtonEnabled = true
        googleMap?.uiSettings?.isZoomControlsEnabled = true
        val paddingBottom = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 108F, resources.displayMetrics))
        googleMap?.setPadding(0, 0, 0, paddingBottom)
        val locationRequest: LocationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 2000
        locationRequest.fastestInterval = 1000
        mFusedLocationProviderClient?.requestLocationUpdates(locationRequest, myLocationCallback, Looper.myLooper())
    }

    private fun getDirection(marker: Marker?){
        val tag: MarkerTag = marker?.tag as MarkerTag
        Log.d(AppConstants.TAG_DEBUG, "FragmentMaps # info window clicked $tag")
        if (tag.type==0) {
            if (!progressDialog.isShowing) {
                progressDialog.show()
            }

            val latitude = App.prefHelper?.getString(AppConstants.PREFERENCE_LATITUDE)
            val longitude = App.prefHelper?.getString(AppConstants.PREFERENCE_LONGITUDE)
            origin = LatLng(latitude?.toDouble()!!, longitude?.toDouble()!!)
            destination = LatLng(tag.latitude, tag.longitude)

            if (latitude != "") {
                if (longitude != "") {
                    GoogleDirection.withServerKey(context?.getString(R.string.DIRECTION_API_KEY))
                        .from(origin)
                        .to(destination)
                        .alternativeRoute(true)
                        .transportMode(TransportMode.DRIVING)
                        .avoid(AvoidType.TOLLS)
                        .execute(this)
                } else {
                    AppHelper.displayToastError(context!!, getString(R.string.error_get_user_location))
                }
            } else {
                AppHelper.displayToastError(context!!, getString(R.string.error_get_user_location))
            }
        }

    }

    private fun setCameraWithCoordinationBounds(route: Route){
        val southwest:LatLng = route.bound.southwestCoordination.coordination
        val northeast:LatLng = route.bound.northeastCoordination.coordination
        val bounds = LatLngBounds(southwest, northeast)
        mGoogleMap?.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap
        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.style_map))
        getMapPins(googleMap)
        setMarkerAnimation(googleMap)

        if (AppHelper.checkLocationSetting(context!!)) {
           requestPermission(googleMap)
        } else {
            AppHelper.displayLocationSettingsRequest(activity as Activity)
        }

        googleMap?.setOnInfoWindowClickListener { marker ->
            getDirection(marker)
        }
    }

    override fun onDirectionSuccess(direction: Direction?, rawBody: String?) {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
        if (direction?.isOK!!) {
            if (direction.routeList.size>0) {
                mGoogleMap?.clear()
                mGoogleMap?.addMarker(MarkerOptions().position(origin!!).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_origin)))?.tag = MarkerTag("Origin",3,0.0,0.0)
                mGoogleMap?.addMarker(MarkerOptions().position(destination!!).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_pin)))?.tag = MarkerTag("Destination",3,0.0,0.0)
                setCameraWithCoordinationBounds(direction.routeList[0])
                for (i:Int in 0 until direction.routeList.size) {
                    val color = colors[i % colors.size]
                    val route = direction.routeList[i]
                    val directionPositionList = route.legList[0].directionPoint
                    mGoogleMap?.addPolyline(DirectionConverter.createPolyline(context, directionPositionList, 5, Color.parseColor(color)))
                }
            } else {
                AppHelper.displayToastError(context!!, context?.getString(R.string.error_direction_not_success)!!)
            }
        }
    }

    override fun onDirectionFailure(t: Throwable?) {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
        AppHelper.displayToastError(context!!, context?.getString(R.string.error_direction_not_success)!!)
        t?.printStackTrace()
    }
}