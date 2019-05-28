package com.almas.batuapps.menu.listplace.viewmodels

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.almas.batuapps.menu.listplace.models.ListplaceModel

class CustomDetailListplaceViewModelFactory(private val placeModel: ListplaceModel?, private val application: Application): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailListPlaceViewModel(placeModel, application) as T
    }
}