package com.example.foodapplication.data.remote.network.mapper

import com.example.foodapplication.data.remote.network.model.ChatbotAnswerDto
import com.example.foodapplication.domain.remote.model.GetChatbotAnswer

fun ChatbotAnswerDto.toDomain(): GetChatbotAnswer {
    return GetChatbotAnswer(answerText = answerText)
}