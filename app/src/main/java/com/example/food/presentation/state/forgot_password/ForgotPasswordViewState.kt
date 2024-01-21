package com.example.food.presentation.state.forgot_password

import com.example.food.data.common.Resource

data class ForgotPasswordViewState(
    val resource: Resource<Unit>?,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
