package com.almas.batuapps.menu.listplace.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.almas.batuapps.R
import com.almas.batuapps.databinding.ItemListplaceBinding
import com.almas.batuapps.menu.listplace.models.ListplaceModel
import com.almas.batuapps.menu.listplace.viewmodels.ItemListplaceViewModel

class ListplaceAdapter(
    val context: Context,
    listPlace: MutableList<ListplaceModel.PlaceModel>
): RecyclerView.Adapter<ListplaceAdapter.ItemPlaceViewHolder>() {

    private var listPlace: MutableList<ListplaceModel.PlaceModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListplaceAdapter.ItemPlaceViewHolder {
        val binding: ItemListplaceBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_listplace, parent, false)
        return ItemPlaceViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listPlace.size
    }

    override fun onBindViewHolder(holder: ItemPlaceViewHolder, position: Int) {
        holder.bindData(listPlace[holder.adapterPosition])
    }

    fun setData(list: MutableList<ListplaceModel.PlaceModel>){
        this.listPlace = list
    }

    class ItemPlaceViewHolder(private val binding: ItemListplaceBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindData(model: ListplaceModel.PlaceModel){
            val viewModel = ItemListplaceViewModel(model)
            binding.itemListplace = viewModel
        }
    }
}