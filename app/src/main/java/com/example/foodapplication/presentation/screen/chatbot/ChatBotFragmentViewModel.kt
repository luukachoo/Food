package com.example.foodapplication.presentation.screen.chatbot

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapplication.data.common.ResourceApi
import com.example.foodapplication.domain.remote.use_case.chatbot.GetChatbotAnswerUseCase
import com.example.foodapplication.presentation.event.chatbot.ChatBotFragmentEvent
import com.example.foodapplication.presentation.mapper.toPresentation
import com.example.foodapplication.presentation.model.ChatbotAnswer
import com.example.foodapplication.presentation.model.Message
import com.example.foodapplication.presentation.state.chatbot.ChatbotViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatBotFragmentViewModel @Inject constructor(
    val chatbotAnswerUseCase: GetChatbotAnswerUseCase
) : ViewModel() {

    private val _chatbotSendMessage = MutableSharedFlow<Message>()
    val chatbotSendMessage get() = _chatbotSendMessage

    private val _chatbotAnswer = MutableSharedFlow<ChatbotAnswer>()
    val chatbotAnswer get() = _chatbotAnswer

    private var currentId = 0

    fun onEvent(event: ChatBotFragmentEvent) {
        viewModelScope.launch {
            when (event) {
                is ChatBotFragmentEvent.FetchChatbotResponse -> fetchChatbotResponse(event.text)
                is ChatBotFragmentEvent.SendMessage -> sendMessage(event.text)
            }
        }
    }

    private fun fetchChatbotResponse(text: String) {
        viewModelScope.launch {
            chatbotAnswerUseCase(text).collect { resource ->
                when (resource) {
                    is ResourceApi.Success -> {
                        _chatbotAnswer.emit(resource.data.toPresentation())
                    }

                    is ResourceApi.Error -> {
                        resource.errorMessage
                    }
                }
            }
        }
    }

    private fun sendMessage(text: String) {
        val userMessage =
            Message(
                id = generateMessageId(),
                text = text,
                type = Message.MessageType.USER
            )

        viewModelScope.launch {
            _chatbotSendMessage.emit(userMessage)
        }
        fetchChatbotResponse(text)
    }

    private fun generateMessageId() = ++currentId

}