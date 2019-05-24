package com.almas.batuapps.menu.listplace.views

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.almas.batuapps.R
import com.almas.batuapps.databinding.FragmentListplaceBinding
import com.almas.batuapps.menu.listplace.viewmodels.FragmentListplaceViewModel

class FragmentListplace: Fragment() {
    companion object {
        fun getInstance(): Fragment {
            return FragmentListplace()
        }
    }

    private lateinit var viewModel: FragmentListplaceViewModel
    private lateinit var binding: FragmentListplaceBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listplace, container, false)
        viewModel = ViewModelProviders.of(this).get(FragmentListplaceViewModel::class.java)
        binding.listplace = viewModel

        return binding.root
    }
}
