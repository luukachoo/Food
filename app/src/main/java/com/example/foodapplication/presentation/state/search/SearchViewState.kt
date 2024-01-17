package com.example.foodapplication.presentation.state.search

import com.example.foodapplication.presentation.model.SearchedRecipesInfo

data class SearchViewState(
    val recipesList: List<SearchedRecipesInfo.SearchedRecipe>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
