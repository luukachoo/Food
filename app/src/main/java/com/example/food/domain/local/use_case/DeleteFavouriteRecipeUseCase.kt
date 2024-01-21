package com.example.food.domain.local.use_case

import com.example.food.domain.local.model.FavouriteRecipeEntity
import com.example.food.domain.local.repository.FavouriteRecipeRepository
import javax.inject.Inject

class DeleteFavouriteRecipeUseCase @Inject constructor(private val favouriteRecipeRepository: FavouriteRecipeRepository) {
    suspend operator fun invoke(recipe: FavouriteRecipeEntity) =
        favouriteRecipeRepository.removeRecipe(recipe)
}