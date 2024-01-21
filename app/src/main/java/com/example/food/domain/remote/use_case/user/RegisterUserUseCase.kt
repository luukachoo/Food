package com.example.food.domain.remote.use_case.user

import com.example.food.data.common.Resource
import com.example.food.data.remote.network.model.UserInfo
import com.example.food.domain.remote.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(
        email: String,
        password: String,
        userInfo: UserInfo
    ): Flow<Resource<FirebaseUser>> {
        return repository.register(email = email, password = password, userInfo = userInfo)
    }
}