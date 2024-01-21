package com.example.food.presentation.state.favourite

import com.example.food.domain.local.model.FavouriteRecipeEntity

data class FavouriteViewState(
    val favouriteRecipes: List<FavouriteRecipeEntity>? = emptyList(),
    val errorMessage: String? = null
)