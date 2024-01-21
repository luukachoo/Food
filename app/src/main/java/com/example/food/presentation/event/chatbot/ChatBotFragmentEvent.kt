package com.example.food.presentation.event.chatbot

sealed class ChatBotFragmentEvent {
    data class FetchChatbotResponse(val text: String) : ChatBotFragmentEvent()
    data class SendMessage(val text: String) : ChatBotFragmentEvent()
}
