package com.example.foodapplication.di

import com.example.foodapplication.data.remote.repository.RecipeRepositoryImpl
import com.example.foodapplication.domain.remote.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RecipeRepositoryModule {
    @Binds
    @Singleton
    fun bindRecipeRepository(repositoryImpl: RecipeRepositoryImpl): RecipeRepository
}