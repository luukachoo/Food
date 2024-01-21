package com.example.food.presentation.mapper

import com.example.food.domain.remote.model.GetChatbotAnswer
import com.example.food.presentation.model.ChatbotAnswer

fun GetChatbotAnswer.toPresentation(): ChatbotAnswer {
    return ChatbotAnswer(answerText = answerText)
}