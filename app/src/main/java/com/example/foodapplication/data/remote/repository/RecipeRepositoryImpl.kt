package com.example.foodapplication.data.remote.repository

import com.example.foodapplication.data.common.ResourceApi
import com.example.foodapplication.data.common.ResponseHandler
import com.example.foodapplication.data.local.dao.FavouriteRecipeDao
import com.example.foodapplication.data.remote.network.mapper.base.asResource
import com.example.foodapplication.data.remote.network.mapper.toDomain
import com.example.foodapplication.data.remote.service.RecipeService
import com.example.foodapplication.domain.local.model.FavouriteRecipeEntity
import com.example.foodapplication.domain.remote.model.GetDetailedRecipeInfo
import com.example.foodapplication.domain.remote.model.GetSearchedRecipesInfo
import com.example.foodapplication.domain.remote.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val service: RecipeService,
    private val responseHandler: ResponseHandler,
    private val favouriteRecipeDao: FavouriteRecipeDao
) : RecipeRepository {
    override suspend fun getRecipes(): Flow<ResourceApi<GetSearchedRecipesInfo>> {
        return responseHandler.handleApiCall {
            service.getRecipes()
        }.asResource { it.toDomain() }
    }

    override suspend fun getDetailsRecipe(itemId: Int): Flow<ResourceApi<GetDetailedRecipeInfo>> {
        return responseHandler.handleApiCall {
            service.getDetailsRecipe(itemId)
        }.asResource { it.toDomain() }
    }

    override suspend fun getRecipeByTitle(title: String): Flow<ResourceApi<GetSearchedRecipesInfo>> {
        return responseHandler.handleApiCall {
            service.getRecipes(titleMatch = title)
        }.asResource { it.toDomain() }
    }

    override suspend fun getFavourites(): List<FavouriteRecipeEntity> =
        favouriteRecipeDao.getAllRecipes()

    override suspend fun addRecipe(recipe: FavouriteRecipeEntity) =
        favouriteRecipeDao.addRecipe(recipe)

    override suspend fun removeRecipe(recipe: FavouriteRecipeEntity) =
        favouriteRecipeDao.removeRecipe(recipe)
}