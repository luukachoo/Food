package com.example.foodapplication.data.remote.service

import com.example.foodapplication.data.common.ApiKey
import com.example.foodapplication.data.remote.network.model.ChatbotAnswerDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ChatbotService {
    @GET("food/converse")
    @Headers("X-Api-Key: ${ApiKey.KEY}")
    suspend fun getChatbotAnswer(@Query("text") text: String): Response<ChatbotAnswerDto>
}