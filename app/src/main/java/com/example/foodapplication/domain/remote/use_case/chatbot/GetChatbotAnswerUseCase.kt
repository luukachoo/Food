package com.example.foodapplication.domain.remote.use_case.chatbot

import com.example.foodapplication.data.common.ResourceApi
import com.example.foodapplication.domain.remote.model.GetChatbotAnswer
import com.example.foodapplication.domain.remote.repository.ChatbotRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatbotAnswerUseCase @Inject constructor(
    private val chatbotRepository: ChatbotRepository
) {
    suspend operator fun invoke(text: String): Flow<ResourceApi<GetChatbotAnswer>> {
        return chatbotRepository.getAnswer(text = text)
    }
}