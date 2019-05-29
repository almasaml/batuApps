package com.almas.batuapps.networks

import com.almas.batuapps.menu.gallery.models.GalleryModel
import com.almas.batuapps.menu.listplace.models.ListplaceModel
import io.reactivex.Observable
import retrofit2.http.GET

interface RestApi {
    @GET("List_place_malang_batu.json")
    fun getListPlace(): Observable<MutableList<ListplaceModel>>

//    @GET("List_place_kab_malang.json")
//    fun getListPlaceKabMalang(): Observable<MutableList<ListplaceModel>>
//
//    @GET("List_place_kota_batu.json")
//    fun getListPlaceKotaBatu(): Observable<MutableList<ListplaceModel>>
//
//    @GET("List_place_kota_malang.json")
//    fun getListPlaceKotaMalang(): Observable<MutableList<ListplaceModel>>

    @GET("Gallery_Malang_Batu.json")
    fun getGallery(): Observable<MutableList<GalleryModel>>

    //@GET("Maps_Malang_Batu.json ")
    //fun getMapPins(): Observable<MutableList<PinModel>>
}