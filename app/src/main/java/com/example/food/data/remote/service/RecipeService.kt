package com.example.food.data.remote.service

import com.example.food.data.common.ApiKey
import com.example.food.data.remote.network.model.DetailedRecipeInfoDto
import com.example.food.data.remote.network.model.SearchedRecipesInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeService {
    @GET("recipes/complexSearch")
    @Headers("X-Api-Key: ${ApiKey.KEY}")
    suspend fun getRecipes(@Query("titleMatch") titleMatch: String? = null): Response<SearchedRecipesInfoDto>

    @GET("recipes/{id}/information")
    @Headers("X-Api-Key: ${ApiKey.KEY}")
    suspend fun getDetailsRecipe(@Path("id") id: Int): Response<DetailedRecipeInfoDto>

}