package com.example.food.domain.remote.use_case.recipe

import com.example.food.data.common.ResourceApi
import com.example.food.domain.remote.model.GetSearchedRecipesInfo
import com.example.food.domain.remote.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeByTitleUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(title: String): Flow<ResourceApi<GetSearchedRecipesInfo>> {
        return recipeRepository.getRecipeByTitle(title = title)
    }
}