package com.tie.admindreamsquad.ui.dashboard

import android.os.Bundle
import android.view.MenuItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.tie.admindreamsquad.R
import com.tie.admindreamsquad.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!! // Non-nullable property for safe access

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize view binding
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController(view)

        val configuration: AppBarConfiguration = AppBarConfiguration.Builder(navController.graph)
            .setDrawerLayout(binding.drawerLayout).build()

        setupWithNavController(binding.navigationDrawer, navController)
        setupWithNavController(binding.toolbar, navController, configuration)

        // Set the NavigationItemSelectedListener
        binding.navigationDrawer.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation item clicks here
        when (item.itemId) {
//            R.id.some_menu_item_id -> {
//                // Perform action for this menu item
//                return true
//            }
//            R.id.another_menu_item_id -> {
//                // Perform another action
//                return true
//            }
        }
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leaks
    }
}
