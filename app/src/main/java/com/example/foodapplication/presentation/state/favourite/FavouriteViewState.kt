package com.example.foodapplication.presentation.state.favourite

import com.example.foodapplication.domain.local.model.FavouriteRecipeEntity

data class FavouriteViewState(
    val favouriteRecipes: List<FavouriteRecipeEntity>? = emptyList(),
    val errorMessage: String? = null
)