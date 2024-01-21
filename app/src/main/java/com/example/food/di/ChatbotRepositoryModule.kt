package com.example.food.di

import com.example.food.data.remote.repository.ChatbotRepositoryImpl
import com.example.food.domain.remote.repository.ChatbotRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ChatbotRepositoryModule {
    @Binds
    @Singleton
    fun bindChatbotRepository(chatbotRepositoryImpl: ChatbotRepositoryImpl): ChatbotRepository
}