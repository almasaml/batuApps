package com.almas.batuapps.menu.listplace.views

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.almas.batuapps.R
import com.almas.batuapps.databinding.ActivityDetailListplaceBinding
import com.almas.batuapps.menu.listplace.models.ListplaceModel
import com.almas.batuapps.menu.listplace.viewmodels.CustomDetailListplaceViewModelFactory
import com.almas.batuapps.menu.listplace.viewmodels.DetailListPlaceViewModel

class DetailListPlaceActivity: AppCompatActivity(){
    companion object {
        const val EXTRA_DATA_LISTPLACE = "extra_data_list_place"
    }

    private lateinit var binding: ActivityDetailListplaceBinding
    private lateinit var viewModel: DetailListPlaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_listplace)
        val placeModel: ListplaceModel? = intent?.getParcelableExtra(EXTRA_DATA_LISTPLACE)
        viewModel = ViewModelProviders.of(this, CustomDetailListplaceViewModelFactory(placeModel, application)).get(DetailListPlaceViewModel::class.java)
        binding.listPlace = viewModel

    }

}