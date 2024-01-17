package com.example.foodapplication.presentation.event.register

sealed interface RegisterNavigationEvents {
    data object NavigateToLogin : RegisterNavigationEvents
}