package com.example.foodapplication.domain.remote.use_case.validator

import javax.inject.Inject

class DoPasswordsMatchUseCase @Inject constructor() {
    operator fun invoke(password: String, repeatPassword: String): Boolean = password == repeatPassword
}