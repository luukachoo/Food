package com.example.foodapplication.presentation.event.home

sealed interface HomeNavigationEvents {
    data class NavigateToDetails(val id: Int) : HomeNavigationEvents
    data object NavigateToSearch : HomeNavigationEvents
}