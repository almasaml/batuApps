package com.almas.batuapps.menu.other.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.almas.batuapps.menu.other.views.ChildFragmentChangeLog
import com.almas.batuapps.menu.other.views.ChildFragmentInfo
import com.almas.batuapps.menu.other.views.ChildFragmentLibraries
import com.almas.batuapps.menu.other.views.ChildFragmentThanks

class TabOtherAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ChildFragmentInfo.getInstance()
            1 -> ChildFragmentChangeLog.getInstance()
            2 -> ChildFragmentThanks.getInstance()
            3 -> ChildFragmentLibraries.getInstance()
            else -> ChildFragmentInfo.getInstance()
        }
    }

    override fun getCount(): Int {
        return 4
    }
}