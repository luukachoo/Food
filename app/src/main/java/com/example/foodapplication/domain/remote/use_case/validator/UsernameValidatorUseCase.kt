package com.example.foodapplication.domain.remote.use_case.validator

import javax.inject.Inject

class UsernameValidatorUseCase @Inject constructor() {
    operator fun invoke(username: String): Boolean = username.isNotBlank()
}