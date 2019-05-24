package com.almas.batuapps.main.views

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.view.WindowManager
import com.almas.batuapps.R
import com.almas.batuapps.databinding.ActivityMainBinding
import com.almas.batuapps.main.viewmodels.MainViewModel
import com.almas.batuapps.menu.listplace.views.FragmentListplace

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

        setupToolbar()
        setupButtomNavigation()

        supportFragmentManager.beginTransaction().replace(R.id.fl_container, FragmentListplace.getInstance())
    }

    private fun setupToolbar(){
    }

    private fun setupButtomNavigation(){
        binding.bnMain.itemIconTintList = null

    }
}
