package com.example.foodapplication.domain.remote.use_case.user

import com.example.foodapplication.data.common.Resource
import com.example.foodapplication.domain.remote.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Flow<Resource<FirebaseUser>> {
        return repository.login(email, password)
    }
}