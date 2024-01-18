package com.example.foodapplication.domain.remote.repository

import com.example.foodapplication.data.common.ResourceApi
import com.example.foodapplication.domain.remote.model.GetChatbotAnswer
import kotlinx.coroutines.flow.Flow

interface ChatbotRepository {
    suspend fun getAnswer(text: String): Flow<ResourceApi<GetChatbotAnswer>>
}