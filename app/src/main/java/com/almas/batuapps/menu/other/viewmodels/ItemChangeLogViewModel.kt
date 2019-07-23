package com.almas.batuapps.menu.other.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.almas.batuapps.menu.other.models.ChangeLogModel
import com.almas.batuapps.utils.AppHelper

class ItemChangeLogViewModel(changeLogModel: ChangeLogModel?): ViewModel() {
    var version: ObservableField<String> = ObservableField(changeLogModel?.title!!)
    var description: ObservableField<String> = ObservableField(AppHelper.fromHtml(changeLogModel?.description!!))
    var isLast: ObservableField<Boolean> = ObservableField(changeLogModel?.isLast!!)
}