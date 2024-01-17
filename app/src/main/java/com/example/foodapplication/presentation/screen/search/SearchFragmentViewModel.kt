package com.example.foodapplication.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapplication.data.common.ResourceApi
import com.example.foodapplication.domain.remote.use_case.recipe.GetRecipeByTitleUseCase
import com.example.foodapplication.presentation.event.search.SearchFragmentEvents
import com.example.foodapplication.presentation.event.search.SearchNavigationEvents
import com.example.foodapplication.presentation.mapper.toPresentation
import com.example.foodapplication.presentation.state.search.SearchViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(private val getRecipeByTitle: GetRecipeByTitleUseCase) :
    ViewModel() {
    private val _searchViewState = MutableStateFlow(SearchViewState(null))
    val searchViewState get() = _searchViewState

    private val _searchUiEvent = MutableSharedFlow<SearchNavigationEvents>()
    val searchUiEvent: SharedFlow<SearchNavigationEvents> get() = _searchUiEvent

    fun onEvent(event: SearchFragmentEvents) {
        viewModelScope.launch {
            when (event) {
                is SearchFragmentEvents.FetchRecipesByTitle -> fetchRecipesByTitle(event.title)
                is SearchFragmentEvents.ItemClick -> {
                    updateNavigationEvent(SearchNavigationEvents.NavigateToDetails(event.id))
                }

                is SearchFragmentEvents.ResetErrorMessage -> updateErrorMessage(null)
            }
        }
    }

    private fun fetchRecipesByTitle(title: String) {
        viewModelScope.launch {
            getRecipeByTitle(title).collect { resource ->
                _searchViewState.update { it.copy(isLoading = true) }
                when (resource) {
                    is ResourceApi.Success -> {
                        _searchViewState.update { currentState ->
                            currentState.copy(
                                recipesList = resource.data.results.map { searchedRecipe ->
                                    searchedRecipe.toPresentation()
                                },
                                isLoading = false
                            )
                        }
                    }

                    is ResourceApi.Error -> updateErrorMessage(resource.errorMessage)
                }
            }
        }
    }

    private suspend fun updateNavigationEvent(events: SearchNavigationEvents) =
        _searchUiEvent.emit(events)

    private fun updateErrorMessage(message: String?) {
        _searchViewState.update { currentState -> currentState.copy(errorMessage = message) }
    }
}