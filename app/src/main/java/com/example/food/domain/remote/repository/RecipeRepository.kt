package com.example.food.domain.remote.repository

import com.example.food.data.common.ResourceApi
import com.example.food.domain.local.model.FavouriteRecipeEntity
import com.example.food.domain.remote.model.GetDetailedRecipeInfo
import com.example.food.domain.remote.model.GetSearchedRecipesInfo
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipes(): Flow<ResourceApi<GetSearchedRecipesInfo>>
    suspend fun getDetailsRecipe(itemId: Int): Flow<ResourceApi<GetDetailedRecipeInfo>>
    suspend fun getRecipeByTitle(title: String): Flow<ResourceApi<GetSearchedRecipesInfo>>
    suspend fun getFavourites(): List<FavouriteRecipeEntity>
    suspend fun addRecipe(recipe: FavouriteRecipeEntity)
    suspend fun removeRecipe(recipe: FavouriteRecipeEntity)
}