package com.almas.batuapps.menu.gallery.viewmodels

import androidx.lifecycle.ViewModel
import android.content.Intent
import androidx.databinding.ObservableField
import androidx.fragment.app.FragmentActivity
import android.util.Log
import android.view.View
import com.almas.batuapps.data.AppConstants
import com.almas.batuapps.menu.gallery.models.GalleryModel
import com.almas.batuapps.menu.gallery.views.DetailGalleryActivity
import java.lang.ref.WeakReference

class ItemGalleryViewModel(private val weakContext: WeakReference<FragmentActivity>, private val galleryModel: GalleryModel) : ViewModel() {
    var caption: ObservableField<String> = ObservableField(galleryModel.caption!!)
    var thumbnail: ObservableField<String> = ObservableField(galleryModel.thumbnail!!)
    var imageUrl: ObservableField<String> = ObservableField(galleryModel.image!!)

    fun itemClick(view: View){
        Log.d(AppConstants.TAG_DEBUG,"ItemGalleryViewModel # $galleryModel")
        val intent = Intent(weakContext.get(), DetailGalleryActivity::class.java)
        intent.putExtra(DetailGalleryActivity.EXTRA_DATA_GALLERY, galleryModel)
        weakContext.get()?.startActivity(intent)
    }
}