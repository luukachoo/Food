package com.example.food.presentation.state.detail

import com.example.food.domain.local.model.FavouriteRecipeEntity
import com.example.food.presentation.model.DetailedRecipeInfo

data class DetailViewState(
    val recipe: DetailedRecipeInfo? = null,
    val favouriteRecipes: List<FavouriteRecipeEntity>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)