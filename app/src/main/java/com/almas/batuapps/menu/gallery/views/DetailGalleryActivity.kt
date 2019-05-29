package com.almas.batuapps.menu.gallery.views

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.almas.batuapps.R
import com.almas.batuapps.menu.gallery.viewmodels.DetailGalleryViewModel
import com.almas.batuapps.databinding.ActivityDetailGalleryBinding
import com.almas.batuapps.menu.gallery.models.GalleryModel
import com.almas.batuapps.menu.gallery.viewmodels.CustomDetailGalleryViewModelFactory

class DetailGalleryActivity: AppCompatActivity(){
    companion object{
        const val EXTRA_DATA_GALLERY = "extra_data_gallery"
    }

    private lateinit var viewModel : DetailGalleryViewModel
    private lateinit var binding : ActivityDetailGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_gallery)
        val galleryModel: GalleryModel? = intent?.getParcelableExtra(EXTRA_DATA_GALLERY)
        viewModel = ViewModelProviders.of(this, CustomDetailGalleryViewModelFactory(galleryModel, application)).get(DetailGalleryViewModel::class.java)
        binding.detailGallery = viewModel
    }


}