package com.example.foodapplication.di

import com.example.foodapplication.data.remote.repository.AuthRepositoryImpl
import com.example.foodapplication.domain.remote.repository.AuthRepository
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