package com.almas.batuapps.menu.other.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.almas.batuapps.R
import com.almas.batuapps.menu.other.models.ChangeLogModel

class ChildFragmentChangeLogViewModel: ViewModel() {

    private var listChangelog: MutableList<ChangeLogModel> = mutableListOf()
    var listData: MutableLiveData<MutableList<ChangeLogModel>> = MutableLiveData()

    fun setupData(context: Context) {
        listChangelog.clear()
        listChangelog.add(ChangeLogModel(context.getString(R.string.ver_2),context.getString(R.string.ver_2_info), false))
        listChangelog.add(ChangeLogModel(context.getString(R.string.ver_1),context.getString(R.string.ver_1_info), true))
        listData.value = listChangelog
    }
}