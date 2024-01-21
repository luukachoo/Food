package com.example.food.presentation.event.log_in

sealed interface LoginNavigationEvents {
    data object NavigateToHome : LoginNavigationEvents
    data object NavigateToRegister : LoginNavigationEvents
    data object NavigateToForgotPassword : LoginNavigationEvents
}