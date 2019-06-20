package com.almas.batuapps.menu.listplace.viewmodels

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.almas.batuapps.data.AppConstants
import com.almas.batuapps.menu.listplace.models.ListplaceModel
import com.almas.batuapps.menu.listplace.views.DetailListPlaceActivity
import java.lang.ref.WeakReference

class ItemListplaceViewModel(private val weakContext: WeakReference<FragmentActivity>, private val placeModel : ListplaceModel): ViewModel() {

    var title: ObservableField<String> = ObservableField(placeModel.name!!)
    var location: ObservableField<String> = ObservableField(placeModel.location!!)
    var imageUrl: ObservableField<String> = ObservableField(placeModel.image!!)


    fun itemClick(view: View){
        Log.d(AppConstants.TAG_DEBUG,"ItemListplaceViewModel # $placeModel")
        val intent = Intent(weakContext.get(), DetailListPlaceActivity::class.java)
        intent.putExtra(DetailListPlaceActivity.EXTRA_DATA_LISTPLACE, placeModel)
        weakContext.get()?.startActivity(intent)
    }

}

