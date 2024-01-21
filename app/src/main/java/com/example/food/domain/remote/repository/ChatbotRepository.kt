package com.example.food.domain.remote.repository

import com.example.food.data.common.ResourceApi
import com.example.food.domain.remote.model.GetChatbotAnswer
import kotlinx.coroutines.flow.Flow

interface ChatbotRepository {
    suspend fun getAnswer(text: String): Flow<ResourceApi<GetChatbotAnswer>>
}