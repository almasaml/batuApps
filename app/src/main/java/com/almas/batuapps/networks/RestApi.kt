package com.almas.batuapps.networks

import com.almas.batuapps.menu.listplace.models.ListplaceModel
import io.reactivex.Observable
import retrofit2.http.GET

interface RestApi {
    @GET("Sample.json")
    fun getListPlace(): Observable<ListplaceModel>
}