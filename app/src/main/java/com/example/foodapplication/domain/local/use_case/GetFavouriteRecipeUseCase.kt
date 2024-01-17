package com.example.foodapplication.domain.local.use_case

import com.example.foodapplication.domain.local.repository.FavouriteRecipeRepository
import javax.inject.Inject

class GetFavouriteRecipeUseCase @Inject constructor(private val favouriteRecipeRepository: FavouriteRecipeRepository) {
    suspend operator fun invoke(title: String) =
        favouriteRecipeRepository.getRecipe(title)
}