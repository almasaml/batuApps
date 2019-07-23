package com.almas.batuapps.menu.other.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.almas.batuapps.R
import com.almas.batuapps.databinding.ItemLibrariesBinding
import com.almas.batuapps.menu.other.models.LibraryModel
import com.almas.batuapps.menu.other.viewmodels.ItemLibrariesViewModel

class LibraryAdapter(var context: Context, var listLibraries: MutableList<LibraryModel>): RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val binding: ItemLibrariesBinding  = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_libraries, parent, false)
        return LibraryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listLibraries.size
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        holder.bindData(listLibraries[holder.adapterPosition])
    }

    class LibraryViewHolder(val binding: ItemLibrariesBinding?) : RecyclerView.ViewHolder(binding?.root!!){
        fun bindData(libraryModel: LibraryModel) {
            binding?.library = ItemLibrariesViewModel(libraryModel)
        }
    }
}