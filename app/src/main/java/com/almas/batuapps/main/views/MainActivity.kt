package com.almas.batuapps.main.views

import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.almas.batuapps.R
import com.almas.batuapps.databinding.ActivityMainBinding
import com.almas.batuapps.main.viewmodels.MainViewModel
import com.almas.batuapps.menu.gallery.views.FragmentGallery
import com.almas.batuapps.menu.listplace.views.FragmentListplace
import com.almas.batuapps.menu.maps.views.FragmentMaps
import com.almas.batuapps.menu.other.views.FragmentOther
import com.almas.batuapps.utils.AppHelper
import com.almas.batuapps.utils.BottomNavigationViewHelper

class MainActivity : AppCompatActivity() {

    companion object {
        var BACK_PRESSED: Long = 0L
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel =ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.main = viewModel

        setupButtomNavigation()

        supportFragmentManager.beginTransaction().replace(R.id.fl_container, FragmentListplace.getInstance()).commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in supportFragmentManager.fragments){
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onBackPressed() {
        if ((BACK_PRESSED+2000L) > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            AppHelper.displayToastNormal(this, "Tekan lagi")
        }
        BACK_PRESSED = System.currentTimeMillis()
    }

    private fun setupButtomNavigation(){
        binding.bnMain.itemIconTintList = null
        BottomNavigationViewHelper.disableShiftMode(binding.bnMain)
        binding.bnMain.setOnNavigationItemSelectedListener {
            when {
                it.itemId == R.id.menuList -> supportFragmentManager.beginTransaction().replace(R.id.fl_container, FragmentListplace.getInstance()).commit()
                it.itemId == R.id.menuGallery -> supportFragmentManager.beginTransaction().replace(R.id.fl_container, FragmentGallery.getInstance()).commit()
                it.itemId == R.id.menuPeta -> supportFragmentManager.beginTransaction().replace(R.id.fl_container, FragmentMaps.getInstance()).commit()
                else ->  supportFragmentManager.beginTransaction().replace(R.id.fl_container, FragmentOther.getInstance()).commit()
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
