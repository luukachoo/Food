package com.example.foodapplication.presentation.event.register

sealed class RegisterFragmentEvents {
    data class Register(
        val username: String,
        val email: String,
        val password: String,
        val repeatPassword: String
    ) :
        RegisterFragmentEvents()

    data object ResetErrorMessage : RegisterFragmentEvents()
}