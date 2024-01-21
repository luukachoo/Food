package com.example.food.data.remote.repository

import com.example.food.data.common.ResourceApi
import com.example.food.data.common.ResponseHandler
import com.example.food.data.remote.network.mapper.base.asResource
import com.example.food.data.remote.network.mapper.toDomain
import com.example.food.data.remote.service.ChatbotService
import com.example.food.domain.remote.model.GetChatbotAnswer
import com.example.food.domain.remote.repository.ChatbotRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatbotRepositoryImpl @Inject constructor(
    private val service: ChatbotService,
    private val responseHandler: ResponseHandler
) : ChatbotRepository {
    override suspend fun getAnswer(text: String): Flow<ResourceApi<GetChatbotAnswer>> {
        return responseHandler.handleApiCall {
            service.getChatbotAnswer(text)
        }.asResource { it.toDomain() }
    }
}