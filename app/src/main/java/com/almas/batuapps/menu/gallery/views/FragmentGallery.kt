package com.almas.batuapps.menu.gallery.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.almas.batuapps.R
import com.almas.batuapps.databinding.FragmentGalleryBinding
import com.almas.batuapps.menu.gallery.adapters.GalleryAdapter
import com.almas.batuapps.menu.gallery.models.GalleryModel
import com.almas.batuapps.menu.gallery.viewmodels.FragmentGalleryViewModel


class FragmentGallery: Fragment() {
    companion object {
        fun getInstance(): Fragment {
            return FragmentGallery()
        }
    }

    private lateinit var viewModel: FragmentGalleryViewModel
    private lateinit var binding: FragmentGalleryBinding
    private lateinit var adapter: GalleryAdapter
    private var DataGallery: MutableList<GalleryModel> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false)
        viewModel = ViewModelProviders.of(this).get(FragmentGalleryViewModel::class.java)
        binding.gallery = viewModel

        setupRecycler()

        viewModel.getGallery()
        viewModel.listGalleryResponse.observe(this, Observer {
            onListDataChanged(it)
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.destroy()
    }

    private fun setupRecycler(){
        val layoutManager = GridLayoutManager(context, 3)
        binding.rvGallery.layoutManager = layoutManager
        adapter = GalleryAdapter(activity!!, DataGallery)
        binding.rvGallery.adapter = adapter
    }

    private fun onListDataChanged(listData: MutableList<GalleryModel>?){
        if (listData?.isNotEmpty()!!){
            DataGallery.clear()
            DataGallery.addAll(listData)
            binding.rvGallery.post{
            adapter.notifyDataSetChanged()
            }
        }
    }
}