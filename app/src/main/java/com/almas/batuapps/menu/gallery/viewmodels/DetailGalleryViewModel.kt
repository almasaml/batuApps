package com.almas.batuapps.menu.gallery.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import com.almas.batuapps.menu.gallery.models.GalleryModel

class DetailGalleryViewModel(placeModel: GalleryModel?, application: Application) : AndroidViewModel(application){

    var judul: ObservableField<String> = ObservableField(placeModel?.caption!!)
    var thumb: ObservableField<String> = ObservableField(placeModel?.thumbnail!!)
    var imageUrl: ObservableField<String> = ObservableField(placeModel?.image!!)

}