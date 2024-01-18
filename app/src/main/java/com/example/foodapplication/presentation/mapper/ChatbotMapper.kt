package com.example.foodapplication.presentation.mapper

import com.example.foodapplication.domain.remote.model.GetChatbotAnswer
import com.example.foodapplication.presentation.model.ChatbotAnswer

fun GetChatbotAnswer.toPresentation(): ChatbotAnswer {
    return ChatbotAnswer(answerText = answerText)
}