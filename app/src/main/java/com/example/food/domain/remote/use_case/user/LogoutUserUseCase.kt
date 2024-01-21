package com.example.food.domain.remote.use_case.user

import com.example.food.domain.remote.repository.AuthRepository
import javax.inject.Inject

class LogoutUserUseCase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() {
        repository.logout()
    }
}