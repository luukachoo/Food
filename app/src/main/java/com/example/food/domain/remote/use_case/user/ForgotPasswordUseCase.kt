package com.example.food.domain.remote.use_case.user

import com.example.food.data.common.Resource
import com.example.food.domain.remote.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(val repository: AuthRepository) {
    suspend operator fun invoke(email: String): Flow<Resource<Unit>> {
        return repository.forgotPassword(email)
    }
}