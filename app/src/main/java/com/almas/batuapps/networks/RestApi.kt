package com.almas.batuapps.networks

import com.almas.batuapps.menu.gallery.models.GalleryModel
import com.almas.batuapps.menu.listplace.models.ListplaceModel
import com.almas.batuapps.menu.maps.models.PinModel
import io.reactivex.Observable
import retrofit2.http.GET

interface RestApi {
    @GET("List_place_malang_batu.json")
    fun getListPlace(): Observable<MutableList<ListplaceModel>>

    @GET("Gallery_Malang_Batu.json")
    fun getGallery(): Observable<MutableList<GalleryModel>>

    @GET("Maps_Malang_Batu.json")
    fun getMapPins(): Observable<MutableList<PinModel>>
}