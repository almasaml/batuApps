package com.almas.batuapps.menu.other.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.almas.batuapps.R
import com.almas.batuapps.databinding.ChildFragmentPustakaBinding
import com.almas.batuapps.databinding.ItemLibrariesBinding
import com.almas.batuapps.menu.other.adapters.LibraryAdapter
import com.almas.batuapps.menu.other.models.LibraryModel
import com.almas.batuapps.menu.other.viewmodels.ChildFragmentLibrariesViewModel

class ChildFragmentLibraries: Fragment(){

    companion object{
        fun getInstance(): Fragment {
            return ChildFragmentLibraries()
        }
    }

    private lateinit var viewModel: ChildFragmentLibrariesViewModel
    private lateinit var binding: ChildFragmentPustakaBinding
    private lateinit var adapter: LibraryAdapter
    private var listLibraries: MutableList<LibraryModel> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.child_fragment_pustaka, container, false)
        viewModel = ChildFragmentLibrariesViewModel()
        binding.libraries = viewModel

        setupRecycler()
        viewModel.setupData(context!!)
        viewModel.listData.observe(this, Observer {
            onListDataChanged(it)
        })

        return binding.root
    }

    private fun setupRecycler(){
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerViewListLibraries.layoutManager = layoutManager
        adapter = LibraryAdapter(context!!, listLibraries)
        binding.recyclerViewListLibraries.adapter = adapter
    }

    private fun onListDataChanged(list: MutableList<LibraryModel>?){
        if(list?.isNotEmpty()!!){
            listLibraries.clear()
            listLibraries.addAll(list)
            binding.recyclerViewListLibraries.post {
                adapter.notifyDataSetChanged()
            }
        }
    }

}