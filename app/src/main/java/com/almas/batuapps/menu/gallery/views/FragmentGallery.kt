package com.almas.batuapps.menu.gallery.views

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.almas.batuapps.R
import com.almas.batuapps.databinding.FragmentGalleryBinding
import com.almas.batuapps.menu.gallery.viewmodels.FragmentGalleryViewModel


class FragmentGallery: Fragment() {
    companion object {
        fun getInstance(): Fragment {
            return FragmentGallery()
        }
    }

    private lateinit var viewModel: FragmentGalleryViewModel
    private lateinit var binding: FragmentGalleryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false)
        viewModel = ViewModelProviders.of(this).get(FragmentGalleryViewModel::class.java)
        binding.gallery = viewModel

        return binding.root
    }
}