package com.almas.batuapps.menu.listplace.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.almas.batuapps.menu.listplace.models.ListplaceModel
import com.almas.batuapps.networks.MainRepository

class FragmentListplaceViewModel: ViewModel() {
    private val repository = MainRepository()

    var listplace: MutableLiveData<ListplaceModel> = MutableLiveData()
    var error: MutableLiveData<Throwable> = MutableLiveData()

    fun getListplace(){
        repository.requestListPlace({
            listplace.postValue(it)
        },{
            error.postValue(it)
        })
    }

    override fun onCleared() {
        super.onCleared()
        repository.onDestroy()
    }
}