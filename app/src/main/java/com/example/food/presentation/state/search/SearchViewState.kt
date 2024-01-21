package com.example.food.presentation.state.search

import com.example.food.presentation.model.SearchedRecipesInfo

data class SearchViewState(
    val recipesList: List<SearchedRecipesInfo.SearchedRecipe>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
