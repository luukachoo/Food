package com.example.foodapplication.presentation.event.home

sealed class HomeFragmentEvents {
    data object FetchRecipes : HomeFragmentEvents()
    data object EditTextClick : HomeFragmentEvents()
    data object ResetErrorMessage : HomeFragmentEvents()
    data class ItemClick(val id: Int) : HomeFragmentEvents()
}