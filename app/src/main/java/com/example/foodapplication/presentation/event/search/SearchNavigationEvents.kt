package com.example.foodapplication.presentation.event.search

sealed interface SearchNavigationEvents {
    data class NavigateToDetails(val id: Int) : SearchNavigationEvents
    data object NavigateToHome : SearchNavigationEvents
}