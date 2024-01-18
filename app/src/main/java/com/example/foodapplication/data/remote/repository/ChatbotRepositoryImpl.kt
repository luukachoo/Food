package com.example.foodapplication.data.remote.repository

import com.example.foodapplication.data.common.ResourceApi
import com.example.foodapplication.data.common.ResponseHandler
import com.example.foodapplication.data.remote.network.mapper.base.asResource
import com.example.foodapplication.data.remote.network.mapper.toDomain
import com.example.foodapplication.data.remote.service.ChatbotService
import com.example.foodapplication.domain.remote.model.GetChatbotAnswer
import com.example.foodapplication.domain.remote.repository.ChatbotRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatbotRepositoryImpl @Inject constructor(
    private val service: ChatbotService,
    private val responseHandler: ResponseHandler
): ChatbotRepository {
    override suspend fun getAnswer(text: String): Flow<ResourceApi<GetChatbotAnswer>> {
        return responseHandler.handleApiCall {
            service.getChatbotAnswer(text)
        }.asResource { it.toDomain() }
    }
}