package com.example.foodapplication.presentation.mapper

import com.example.foodapplication.domain.remote.model.GetSearchedRecipesInfo
import com.example.foodapplication.presentation.model.SearchedRecipesInfo

fun GetSearchedRecipesInfo.toPresentation(): SearchedRecipesInfo {
    return SearchedRecipesInfo(
        results = results.map { it.toPresentation() },
        offset = offset,
        number = number,
        totalResults = totalResults
    )
}

fun GetSearchedRecipesInfo.GetSearchedRecipe.toPresentation(): SearchedRecipesInfo.SearchedRecipe {
    return SearchedRecipesInfo.SearchedRecipe(
        id = id,
        title = title,
        image = image,
        imageType = imageType,
        isFavourite = false
    )
}