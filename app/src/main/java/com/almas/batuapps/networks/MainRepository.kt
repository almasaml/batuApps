package com.almas.batuapps.networks

import com.almas.batuapps.menu.listplace.models.ListplaceModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainRepository {
    private val apiService = ServiceFactory.create()
    private val compositeDisposable = CompositeDisposable()

    fun requestListPlace(onResult: (ListplaceModel) -> Unit, onError: (Throwable) -> Unit){
        apiService.getListPlace()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: ApiObserver<ListplaceModel>(compositeDisposable){
                override fun onApiSuccess(data: ListplaceModel) {
                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }
            })
    }

    fun onDestroy(){
        compositeDisposable.clear()
    }
}