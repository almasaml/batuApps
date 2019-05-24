package com.almas.batuapps.menu.maps.views

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.almas.batuapps.R
import com.almas.batuapps.databinding.FragmentMapsBinding
import com.almas.batuapps.menu.maps.viewmodels.FragmentMapsViewModel

class FragmentMaps: Fragment() {
    companion object {
        fun getInstance(): Fragment {
            return FragmentMaps()
        }
    }

    private lateinit var viewModel: FragmentMapsViewModel
    private lateinit var binding: FragmentMapsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_maps, container, false)
        viewModel = ViewModelProviders.of(this).get(FragmentMapsViewModel::class.java)
        binding.maps = viewModel

        return binding.root
    }
}