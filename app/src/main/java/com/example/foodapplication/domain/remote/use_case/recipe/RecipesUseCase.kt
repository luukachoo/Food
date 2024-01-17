package com.example.foodapplication.domain.remote.use_case.recipe

import javax.inject.Inject

data class RecipesUseCase @Inject constructor(
    val getRecipes: GetRecipesUseCase,
    val getDetailedRecipe: GetDetailedRecipeUseCase,
    val getRecipeByTitle: GetRecipeByTitleUseCase
)