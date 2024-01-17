package com.example.foodapplication.domain.remote.use_case.recipe

import com.example.foodapplication.domain.remote.repository.RecipeRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke() = recipeRepository.getRecipes()
}