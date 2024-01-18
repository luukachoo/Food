package com.example.foodapplication.presentation.event.chatbot

sealed class ChatBotFragmentEvent {
    data class FetchChatbotResponse(val text: String) : ChatBotFragmentEvent()
    data class SendMessage(val text: String) : ChatBotFragmentEvent()
}
