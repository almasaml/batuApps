package com.almas.batuapps.menu.listplace.adapters

import android.databinding.DataBindingUtil
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.almas.batuapps.R
import com.almas.batuapps.databinding.ItemListplaceBinding
import com.almas.batuapps.menu.listplace.models.ListplaceModel
import com.almas.batuapps.menu.listplace.viewmodels.ItemListplaceViewModel
import java.lang.ref.WeakReference

class ListplaceAdapter(private val activity: FragmentActivity, private val listData: MutableList<ListplaceModel>): RecyclerView.Adapter<ListplaceAdapter.ListPlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPlaceViewHolder {
        val binding: ItemListplaceBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.item_listplace, parent, false)
        return ListPlaceViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.listData.size
    }

    override fun onBindViewHolder(holder: ListPlaceViewHolder, position: Int) {
        holder.setupData(activity, listData[holder.adapterPosition])
    }

    class ListPlaceViewHolder(val binding: ItemListplaceBinding) : RecyclerView.ViewHolder(binding.root){
        fun setupData(activity: FragmentActivity, placeModel: ListplaceModel){
            val weakContext = WeakReference(activity)
            val viewModel = ItemListplaceViewModel(weakContext, placeModel)
            binding.itemListplace = viewModel
        }
    }
}