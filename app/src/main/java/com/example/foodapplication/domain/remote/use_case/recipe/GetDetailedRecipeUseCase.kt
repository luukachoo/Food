package com.example.foodapplication.domain.remote.use_case.recipe

import com.example.foodapplication.data.common.ResourceApi
import com.example.foodapplication.domain.remote.model.GetDetailedRecipeInfo
import com.example.foodapplication.domain.remote.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailedRecipeUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(itemId: Int): Flow<ResourceApi<GetDetailedRecipeInfo>> {
        return recipeRepository.getDetailsRecipe(itemId = itemId)
    }
}