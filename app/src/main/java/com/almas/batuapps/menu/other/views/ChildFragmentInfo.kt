package com.almas.batuapps.menu.other.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.almas.batuapps.BuildConfig
import com.almas.batuapps.R
import com.almas.batuapps.databinding.ChildFragmentInfoBinding

class ChildFragmentInfo: Fragment() {

    companion object {
        fun getInstance():Fragment {
            return ChildFragmentInfo()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ChildFragmentInfoBinding = DataBindingUtil.inflate(inflater, R.layout.child_fragment_info, container, false)
        binding.textViewVersion.text = getString(R.string.info_app_version, BuildConfig.VERSION_NAME)
        binding.textViewGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/almasaml/batuApps"))
            context?.startActivity(intent)
        }
        return binding.root
    }
}