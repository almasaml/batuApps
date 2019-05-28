package com.almas.batuapps.menu.listplace.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.almas.batuapps.menu.listplace.models.ListplaceModel
import com.almas.batuapps.networks.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FragmentListplaceViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val restApi = ServiceFactory.create()

    var listPlaceResponse : MutableLiveData<MutableList<ListplaceModel>> = MutableLiveData()
    var error: MutableLiveData<Throwable> = MutableLiveData()
    var isLoading: ObservableField<Boolean> = ObservableField()

    fun getListplace(){
        isLoading.set(true)
        compositeDisposable.add(
            restApi.getListPlace()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isLoading.set(false)
                    listPlaceResponse.value = it
                },{
                    isLoading.set(false)
                    error.value = it
                })
        )
    }


    fun destroy(){
        compositeDisposable.clear()
    }
}