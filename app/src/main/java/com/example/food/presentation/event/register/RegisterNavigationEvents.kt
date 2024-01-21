package com.example.food.presentation.event.register

sealed interface RegisterNavigationEvents {
    data object NavigateToLogin : RegisterNavigationEvents
}