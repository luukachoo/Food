package com.example.food.presentation.model

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
