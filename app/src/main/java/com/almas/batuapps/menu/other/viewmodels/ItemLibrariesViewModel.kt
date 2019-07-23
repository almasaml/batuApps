package com.almas.batuapps.menu.other.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.almas.batuapps.menu.other.models.LibraryModel
import com.almas.batuapps.utils.AppHelper

class ItemLibrariesViewModel(libraryModel: LibraryModel): ViewModel() {
    var name: ObservableField<String> = ObservableField(libraryModel.title!!)
    var url: ObservableField<String> = ObservableField(libraryModel.url!!)
    var license: ObservableField<String> = ObservableField(AppHelper.fromHtml(libraryModel.license!!))
    var isLast: ObservableField<Boolean> = ObservableField(libraryModel.isLast!!)
}