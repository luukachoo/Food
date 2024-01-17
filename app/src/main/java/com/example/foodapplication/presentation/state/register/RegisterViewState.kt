package com.example.foodapplication.presentation.state.register

import com.example.foodapplication.data.common.Resource
import com.google.firebase.auth.FirebaseUser

data class RegisterViewState(
    val resource: Resource<FirebaseUser>?,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

