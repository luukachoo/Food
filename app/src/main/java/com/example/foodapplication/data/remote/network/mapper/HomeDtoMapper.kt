package com.example.foodapplication.data.remote.network.mapper

import com.example.foodapplication.data.remote.network.model.SearchedRecipesInfoDto
import com.example.foodapplication.domain.remote.model.GetSearchedRecipesInfo

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