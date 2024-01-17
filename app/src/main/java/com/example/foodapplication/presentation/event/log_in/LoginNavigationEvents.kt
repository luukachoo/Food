package com.example.foodapplication.presentation.event.log_in

sealed interface LoginNavigationEvents {
    data object NavigateToHome : LoginNavigationEvents
    data object NavigateToRegister : LoginNavigationEvents
}