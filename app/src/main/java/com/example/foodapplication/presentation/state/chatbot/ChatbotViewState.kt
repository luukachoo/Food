package com.example.foodapplication.presentation.state.chatbot

import com.example.foodapplication.presentation.model.ChatbotAnswer
import com.example.foodapplication.presentation.model.Message

data class ChatbotViewState(
    val answerText: ChatbotAnswer? = null,
    val errorMessage: String? = null,
    val messages: List<Message> = emptyList()
)
