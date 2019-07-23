package com.almas.batuapps.menu.other.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.almas.batuapps.R
import com.almas.batuapps.databinding.ChildFragmentThanksBinding
import com.almas.batuapps.utils.AppHelper

class ChildFragmentThanks: Fragment(){
    companion object{
        fun getInstance(): Fragment{
            return ChildFragmentThanks()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ChildFragmentThanksBinding = DataBindingUtil.inflate(inflater, R.layout.child_fragment_thanks, container, false)
        binding.textViewThanks.text = AppHelper.fromHtml(getString(R.string.trims))
        return binding.root
    }
}