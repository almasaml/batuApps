package com.almas.batuapps.menu.listplace.viewmodels

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.almas.batuapps.menu.listplace.models.ListplaceModel

class ItemListplaceViewModel(model: ListplaceModel.PlaceModel): ViewModel() {

    var title: ObservableField<String> = ObservableField()
    var location: ObservableField<String> = ObservableField()
    var image: ObservableField<String> = ObservableField()

    init {
        title.set(model.name)
        location.set(model.location)
        image.set(model.image)
    }
}