package com.almas.batuapps.menu.other.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.almas.batuapps.R
import com.almas.batuapps.databinding.ChildFragmentVersiBinding
import com.almas.batuapps.databinding.ItemChangelogBinding
import com.almas.batuapps.menu.other.adapters.ChangeLogAdapter
import com.almas.batuapps.menu.other.models.ChangeLogModel
import com.almas.batuapps.menu.other.viewmodels.ChildFragmentChangeLogViewModel
import com.almas.batuapps.menu.other.viewmodels.ItemChangeLogViewModel

class ChildFragmentChangeLog: Fragment() {

    companion object{
        fun getInstance(): Fragment {
            return ChildFragmentChangeLog()
        }
    }

    private lateinit var binding: ChildFragmentVersiBinding
    private lateinit var viewModel: ChildFragmentChangeLogViewModel
    private lateinit var adapter: ChangeLogAdapter
    private var listChangeLog: MutableList<ChangeLogModel> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.child_fragment_info, container, false)
        viewModel = ViewModelProviders.of(this).get(ChildFragmentChangeLogViewModel::class.java)
        binding.changelog = viewModel

        setupRecycler()

        viewModel.setupData(context!!)
        viewModel.listData.observe(this, Observer {
            onListDataChange(it)
        })

        return binding.root
    }

    private fun setupRecycler() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerViewChangelog.layoutManager = layoutManager
        adapter = ChangeLogAdapter(activity!!, listChangeLog)
        binding.recyclerViewChangelog.adapter = adapter
    }

    private fun onListDataChange(list: MutableList<ChangeLogModel>?){
        if (list?.isNotEmpty()!!){
            listChangeLog.clear()
            listChangeLog.addAll(list)
            binding.recyclerViewChangelog.post {
                adapter.notifyDataSetChanged()
            }
        }
    }
}