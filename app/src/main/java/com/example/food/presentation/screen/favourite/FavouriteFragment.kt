package com.example.food.presentation.screen.favourite

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.food.databinding.FragmentFavouriteBinding
import com.example.food.presentation.common.base.BaseFragment
import com.example.food.presentation.common.helper.Listener
import com.example.food.presentation.common.helper.Observer
import com.example.food.presentation.event.favourite.FavouriteFragmentEvents
import com.example.food.presentation.event.favourite.FavouriteNavigationEvents
import com.example.food.presentation.state.favourite.FavouriteViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>(FragmentFavouriteBinding::inflate),
    Listener, Observer {
    private val viewModel: FavouriteFragmentViewModel by viewModels()
    private val favouritesRecyclerAdapter by lazy { FavouriteFragmentRecyclerAdapter() }
    override fun init() {
        observers()
        listeners()
        setUpRecycler()
        swipeToDelete()
    }

    override fun listeners() {
        favouritesRecyclerAdapter.onItemClick { favRecipe ->
            viewModel.onEvent(FavouriteFragmentEvents.ItemClick(favRecipe.id))
        }

        binding.buttonChatbot.setOnClickListener {
            handleNavigationEvents(FavouriteNavigationEvents.NavigateToChatbot)
        }
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favouriteRecipeState.collect {
                    handleFavouriteRecipeState(state = it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favouriteUiEvent.collect {
                    handleNavigationEvents(it)
                }
            }
        }
    }

    private fun swipeToDelete() = ItemTouchHelper(favoritesSwipeCallback)
        .attachToRecyclerView(binding.rvFavourites)

    private fun handleFavouriteRecipeState(state: FavouriteViewState) {
        state.favouriteRecipes.let {
            favouritesRecyclerAdapter.submitList(it)
        }

        state.errorMessage.let {
            viewModel.onEvent(FavouriteFragmentEvents.ResetErrorMessage)
        }
    }

    private fun handleNavigationEvents(event: FavouriteNavigationEvents) {
        when (event) {
            is FavouriteNavigationEvents.NavigateToDetails -> findNavController().navigate(
                FavouriteFragmentDirections.favouritesFragmentToDetailsFragment(event.id)
            )

            is FavouriteNavigationEvents.NavigateToChatbot -> findNavController().navigate(
                FavouriteFragmentDirections.actionFavouriteFragmentToChatbotFragment()
            )
        }
    }

    private fun setUpRecycler() = with(binding) {
        rvFavourites.adapter = favouritesRecyclerAdapter
        viewModel.onEvent(FavouriteFragmentEvents.GetRecipes)
    }

    private val favoritesSwipeCallback = object :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val item = favouritesRecyclerAdapter.currentList[position]
            val title = item.title
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.onEvent(FavouriteFragmentEvents.SlideToDelete(title = title))
                viewModel.onEvent(FavouriteFragmentEvents.GetRecipes)
            }
        }
    }

}