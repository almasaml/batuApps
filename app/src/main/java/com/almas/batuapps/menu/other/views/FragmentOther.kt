package com.almas.batuapps.menu.other.views

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.almas.batuapps.R
import com.almas.batuapps.databinding.FragmentOtherBinding
import com.almas.batuapps.menu.other.viewmodels.FragmentOtherViewModel


class FragmentOther: Fragment() {

        companion object{
            fun getInstance(): Fragment{
                return FragmentOther()
            }
        }

    private lateinit var viewModel: FragmentOtherViewModel
    private lateinit var binding: FragmentOtherBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_other, container, false)
        viewModel =ViewModelProviders.of(this).get(FragmentOtherViewModel::class.java)
        binding.other = viewModel

        return binding.root
    }
}