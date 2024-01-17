package com.example.foodapplication.presentation.event.favourite

sealed interface FavouriteNavigationEvents {
    data class NavigateToDetails(val id: Int) : FavouriteNavigationEvents
}