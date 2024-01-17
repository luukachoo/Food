package com.example.foodapplication.presentation.state.detail

import com.example.foodapplication.domain.local.model.FavouriteRecipeEntity
import com.example.foodapplication.presentation.model.DetailedRecipeInfo

data class DetailViewState(
    val recipe: DetailedRecipeInfo? = null,
    val favouriteRecipes: List<FavouriteRecipeEntity>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)