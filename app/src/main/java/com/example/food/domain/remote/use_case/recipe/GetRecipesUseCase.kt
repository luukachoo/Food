package com.example.food.domain.remote.use_case.recipe

import com.example.food.domain.remote.repository.RecipeRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke() = recipeRepository.getRecipes()
}