package com.example.foodapplication.domain.remote.use_case.user

import com.example.foodapplication.domain.remote.repository.AuthRepository
import javax.inject.Inject

class LogoutUserUseCase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() {
        repository.logout()
    }
}