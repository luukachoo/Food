package com.example.food.presentation.event.forgot_password

sealed class ForgotPasswordEvents {
    data class SendEmail(val email: String) : ForgotPasswordEvents()
    data object ResetErrorMessage : ForgotPasswordEvents()
}