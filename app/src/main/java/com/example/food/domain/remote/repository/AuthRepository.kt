package com.example.food.domain.remote.repository

import com.example.food.data.common.Resource
import com.example.food.data.remote.network.model.UserInfo
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(
        email: String,
        password: String,
        userInfo: UserInfo
    ): Flow<Resource<FirebaseUser>>

    suspend fun login(email: String, password: String): Flow<Resource<FirebaseUser>>

    suspend fun forgotPassword(email: String): Flow<Resource<Unit>>

    fun logout()
}