package com.example.food.domain.remote.use_case.user

import javax.inject.Inject

data class UserUseCase @Inject constructor(
    val login: LoginUserUseCase,
    val register: RegisterUserUseCase,
    val logout: LogoutUserUseCase
)
