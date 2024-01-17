package com.example.foodapplication.domain.local.use_case

import com.example.foodapplication.domain.local.model.FavouriteRecipeEntity
import com.example.foodapplication.domain.local.repository.FavouriteRecipeRepository
import javax.inject.Inject

class AddFavouriteRecipeUseCase @Inject constructor(private val favouriteRecipeRepository: FavouriteRecipeRepository) {
    suspend operator fun invoke(recipe: FavouriteRecipeEntity) =
        favouriteRecipeRepository.addRecipe(recipe)
}