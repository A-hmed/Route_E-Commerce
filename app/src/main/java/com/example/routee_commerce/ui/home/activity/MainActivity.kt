package com.example.routee_commerce.ui.home.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.routee_commerce.R
import com.example.routee_commerce.databinding.ActivityMainBinding
import com.example.routee_commerce.ui.cart.CartActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        makeStatusBarTransparentAndIconsClear()
        navController = findNavController(R.id.home_host_fragment)
        NavigationUI.setupWithNavController(binding.content.bottomNav, navController)
        initListener()
    }

    private fun initListener() {
        binding.content.header.cart.setOnClickListener {
           navController.navigate(R.id.action_global_cartActivity)
        }
    }

    private fun makeStatusBarTransparentAndIconsClear() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        window.statusBarColor = Color.TRANSPARENT
    }


}
