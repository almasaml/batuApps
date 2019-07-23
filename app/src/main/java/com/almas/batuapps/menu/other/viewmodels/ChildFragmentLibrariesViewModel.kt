package com.almas.batuapps.menu.other.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.almas.batuapps.R
import com.almas.batuapps.menu.other.models.LibraryModel

class ChildFragmentLibrariesViewModel: ViewModel() {

    private var listLibraries: MutableList<LibraryModel> = mutableListOf()
    var listData: MutableLiveData<MutableList<LibraryModel>> = MutableLiveData()

    fun setupData(context: Context){
        listLibraries.clear()
        listLibraries.add(LibraryModel(context.getString(R.string.aosp), context.getString(R.string.aosp_url), context.getString(R.string.aosp_license), false))
        listLibraries.add(LibraryModel(context.getString(R.string.google_direction), context.getString(R.string.google_direction_url), context.getString(R.string.google_direction_license), false))
        listData.value = listLibraries
    }
}