package com.example.foodapplication.presentation.model

data class Message(
    val id: Int,
    val text: String,
    val type: MessageType
) {
    enum class MessageType {
        USER,
        BOT
    }
}
