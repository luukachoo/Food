package com.example.food.presentation.state.home

import com.example.food.presentation.model.SearchedRecipesInfo

data class HomeViewState(
    val recipesList: List<SearchedRecipesInfo.SearchedRecipe>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)