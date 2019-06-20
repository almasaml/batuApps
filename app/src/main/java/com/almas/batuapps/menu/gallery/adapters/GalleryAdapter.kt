package com.almas.batuapps.menu.gallery.adapters

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.almas.batuapps.R
import com.almas.batuapps.databinding.ItemGalleryBinding
import com.almas.batuapps.menu.gallery.models.GalleryModel
import com.almas.batuapps.menu.gallery.viewmodels.ItemGalleryViewModel
import java.lang.ref.WeakReference

class GalleryAdapter(private val activity: FragmentActivity, private val galleryData: MutableList<GalleryModel>): RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding: ItemGalleryBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.item_gallery, parent, false)
        return GalleryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return galleryData.size
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bindData(activity, galleryData[holder.adapterPosition])
    }

    class GalleryViewHolder(val binding: ItemGalleryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindData(activity: FragmentActivity, galleryData: GalleryModel){
            val act = WeakReference<FragmentActivity>(activity)
            val viewModel = ItemGalleryViewModel(act, galleryData)
            binding.itemGallery = viewModel
        }
    }
}