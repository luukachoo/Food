package com.example.food.domain.remote.use_case.chatbot

import com.example.food.data.common.ResourceApi
import com.example.food.domain.remote.model.GetChatbotAnswer
import com.example.food.domain.remote.repository.ChatbotRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatbotAnswerUseCase @Inject constructor(
    private val chatbotRepository: ChatbotRepository
) {
    suspend operator fun invoke(text: String): Flow<ResourceApi<GetChatbotAnswer>> {
        return chatbotRepository.getAnswer(text = text)
    }
}