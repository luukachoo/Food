package com.example.foodapplication.presentation.screen.containers

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.foodapplication.R
import com.example.foodapplication.databinding.FragmentHostBinding
import com.example.foodapplication.presentation.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HostFragment : BaseFragment<FragmentHostBinding>(FragmentHostBinding::inflate) {

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment


    private fun handleNavigationBar() = with(binding) {
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeIcon -> {
                    navController.navigate(R.id.homeNavigation)
                }

                R.id.favouritesIcon -> {
                    navController.navigate(R.id.favouritesNavigation)
                }

                R.id.profileIcon -> {
                    navController.navigate(R.id.profileFragment)
                }
            }
            true
        }
    }

    override fun init() {
        setUpNavGraph()
        handleNavigationBar()
    }

    private fun setUpNavGraph() {
        navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.bottomNavigationView.setupWithNavController(navController)
    }

}