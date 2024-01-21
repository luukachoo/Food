package com.example.food.presentation.event.detail

import com.example.food.domain.local.model.FavouriteRecipeEntity

sealed class DetailFragmentEvent {
    data class FetchRecipe(val itemId: Int) : DetailFragmentEvent()
    data object ResetErrorMessage : DetailFragmentEvent()
    data class AddRecipeToFavourites(val recipe: FavouriteRecipeEntity) : DetailFragmentEvent()
    data class RemoveRecipeFromFavourites(val recipe: FavouriteRecipeEntity) : DetailFragmentEvent()
}