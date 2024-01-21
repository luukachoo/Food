package com.example.food.data.remote.network.mapper

import com.example.food.data.remote.network.model.ChatbotAnswerDto
import com.example.food.domain.remote.model.GetChatbotAnswer

fun ChatbotAnswerDto.toDomain(): GetChatbotAnswer {
    return GetChatbotAnswer(answerText = answerText)
}