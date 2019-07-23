package com.almas.batuapps.menu.other.views

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.almas.batuapps.R
import com.almas.batuapps.databinding.FragmentOtherBinding
import com.almas.batuapps.menu.other.adapters.TabOtherAdapter
import com.google.android.material.appbar.AppBarLayout

class FragmentOther: Fragment() {

        companion object{
            fun getInstance(): Fragment{
                return FragmentOther()
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentOtherBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_other, container, false)

        binding.viewPagerOther.adapter = TabOtherAdapter(childFragmentManager)
        binding.navigationTabStrip.setViewPager(binding.viewPagerOther)
        binding.navigationTabStrip.setTitles("Informasi", "Versi Rilis", "Terima Kasih", "Pustaka")
        binding.navigationTabStrip.inactiveColor = ContextCompat.getColor(context!!, R.color.material_grey_50)
        binding.navigationTabStrip.activeColor = Color.WHITE
        binding.navigationTabStrip.stripColor = ContextCompat.getColor(context!!, R.color.colorAccent)
        binding.navigationTabStrip.titleSize = 25F
        binding.navigationTabStrip.cornersRadius = 0F

        setupAppBar(0F)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        setupAppBar(8F)
    }

    private fun setupAppBar(elevation: Float){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            activity?.findViewById<AppBarLayout>(R.id.appBarMain)?.elevation = elevation
        }
    }
}