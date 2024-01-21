package com.example.food.data.remote.network.mapper

import com.example.food.data.remote.network.model.SearchedRecipesInfoDto
import com.example.food.domain.remote.model.GetSearchedRecipesInfo

fun SearchedRecipesInfoDto.toDomain(): GetSearchedRecipesInfo {
    return GetSearchedRecipesInfo(
        results = results.map { it.toDomain() },
        offset = offset,
        number = number,
        totalResults = totalResults
    )
}

fun SearchedRecipesInfoDto.SearchedRecipeDto.toDomain(): GetSearchedRecipesInfo.GetSearchedRecipe {
    return GetSearchedRecipesInfo.GetSearchedRecipe(
        id = id,
        title = title,
        image = image,
        imageType = imageType
    )
}