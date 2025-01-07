package com.tie.admindreamsquad.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.tie.admindreamsquad.R
import com.tie.admindreamsquad.databinding.ActivityBaseBinding
import com.tie.dreamsquad.utils.GradientAnimatorUtil

class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Initialize components and set up click listeners
        initComponents()
    }
    private fun initComponents() {
        // Start the gradient animation
//        GradientAnimatorUtil.startGradientAnimation(this, binding.main)
        val navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

    }


}