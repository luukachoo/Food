package com.example.foodapplication.domain.remote.repository

import com.example.foodapplication.data.common.Resource
import com.example.foodapplication.data.remote.network.model.UserInfo
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(
        email: String,
        password: String,
        userInfo: UserInfo
    ): Flow<Resource<FirebaseUser>>

    suspend fun login(email: String, password: String): Flow<Resource<FirebaseUser>>
    fun logout()
}