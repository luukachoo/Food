package com.example.food.di

import com.example.food.data.remote.repository.AuthRepositoryImpl
import com.example.food.domain.remote.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface UserRepositoryModule {
    @Binds
    @Singleton
    fun bindUserRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository
}