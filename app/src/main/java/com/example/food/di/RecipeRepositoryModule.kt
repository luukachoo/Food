package com.example.food.di

import com.example.food.data.remote.repository.RecipeRepositoryImpl
import com.example.food.domain.remote.repository.RecipeRepository
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