package com.almas.batuapps.networks

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceFactory {
    fun create(): RestApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://info-malang-batu.firebaseapp.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RestApi::class.java)
    }
}