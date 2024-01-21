package com.example.food.presentation.screen.containers

import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.food.R
import com.example.food.databinding.FragmentHostBinding
import com.example.food.presentation.common.base.BaseFragment
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
        handleBottomNavVisibility()
    }

    private fun setUpNavGraph() {
        navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun handleBottomNavVisibility() = with(binding) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailFragment -> bottomNavigationView.isVisible = false
                R.id.searchFragment -> bottomNavigationView.isVisible = false
                R.id.chatbotFragment -> bottomNavigationView.isVisible = false
                else -> bottomNavigationView.isVisible = true
            }
        }
    }

}