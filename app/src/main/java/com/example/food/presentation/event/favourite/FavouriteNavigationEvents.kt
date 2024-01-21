package com.example.food.presentation.event.favourite

sealed interface FavouriteNavigationEvents {
    data class NavigateToDetails(val id: Int) : FavouriteNavigationEvents
    data object NavigateToChatbot : FavouriteNavigationEvents
}