package com.almas.batuapps.main.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.almas.batuapps.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(ToolbarMain)
        setupNavigation()
    }

    private fun setupNavigation(){
        val navController = Navigation.findNavController(this, R.id.FragmentMain)
        setupActionBarWithNavController(navController)
        NavigationUI.setupWithNavController(ToolbarMain, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.FragmentMain).navigateUp()
    }
}