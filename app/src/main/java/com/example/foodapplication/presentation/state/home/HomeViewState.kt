package com.example.foodapplication.presentation.state.home

import com.example.foodapplication.presentation.model.SearchedRecipesInfo

data class HomeViewState(
    val recipesList: List<SearchedRecipesInfo.SearchedRecipe>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)