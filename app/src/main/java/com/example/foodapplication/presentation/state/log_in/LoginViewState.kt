package com.example.foodapplication.presentation.state.log_in

import com.example.foodapplication.data.common.Resource
import com.google.firebase.auth.FirebaseUser

data class LoginViewState(
    val resource: Resource<FirebaseUser>?,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
