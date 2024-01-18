package com.example.foodapplication.domain.remote.use_case.user

import com.example.foodapplication.data.common.Resource
import com.example.foodapplication.data.remote.network.model.UserInfo
import com.example.foodapplication.domain.remote.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String, userInfo: UserInfo): Flow<Resource<FirebaseUser>> {
        return repository.register(email = email, password = password, userInfo = userInfo)
    }
}