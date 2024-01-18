package com.example.foodapplication.data.remote.repository

import com.example.foodapplication.data.common.Resource
import com.example.foodapplication.data.remote.network.model.UserInfo
import com.example.foodapplication.data.remote.utils.HandleFirebaseResponse
import com.example.foodapplication.domain.remote.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val handleFirebaseResponse: HandleFirebaseResponse
) : AuthRepository {
    override suspend fun register(email: String, password: String, userInfo: UserInfo): Flow<Resource<FirebaseUser>> {
        return handleFirebaseResponse.safeApiCall {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user
            firebaseUser!!
        }
    }

    override suspend fun login(email: String, password: String): Flow<Resource<FirebaseUser>> {
        return handleFirebaseResponse.safeApiCall {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user
            firebaseUser!!
        }
    }

    override fun logout() = firebaseAuth.signOut()

}