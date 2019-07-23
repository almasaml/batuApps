package com.almas.batuapps.menu.other.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.almas.batuapps.R
import com.almas.batuapps.databinding.ItemChangelogBinding
import com.almas.batuapps.menu.other.models.ChangeLogModel
import com.almas.batuapps.menu.other.viewmodels.ItemChangeLogViewModel

class ChangeLogAdapter(val activity: FragmentActivity, private var listChangeLog: MutableList<ChangeLogModel>): RecyclerView.Adapter<ChangeLogAdapter.ChangeLogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChangeLogViewHolder {
        val binding: ItemChangelogBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.item_changelog, parent, false)
        return ChangeLogViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listChangeLog.size
    }

    override fun onBindViewHolder(holder: ChangeLogViewHolder, position: Int) {
        val fixPosition = holder.adapterPosition
        holder.bindData(listChangeLog[fixPosition])
    }

    class ChangeLogViewHolder(val binding: ItemChangelogBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(changeLogModel: ChangeLogModel?){
            binding.changelog = ItemChangeLogViewModel(changeLogModel)
        }

    }
}