package com.almas.batuapps.menu.gallery.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import com.almas.batuapps.menu.gallery.models.GalleryModel
import com.almas.batuapps.networks.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FragmentGalleryViewModel : ViewModel() {

    private val restApi = ServiceFactory.create()
    private val compositeDisposable = CompositeDisposable()
    var isLoading: ObservableField<Boolean> = ObservableField()

    var listGalleryResponse: MutableLiveData<MutableList<GalleryModel>> = MutableLiveData()
    var error: MutableLiveData<Throwable> = MutableLiveData()

    fun getGallery(){
        isLoading.set(true)
        compositeDisposable.add(
            restApi.getGallery()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isLoading.set(false)
                    listGalleryResponse.value = it
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