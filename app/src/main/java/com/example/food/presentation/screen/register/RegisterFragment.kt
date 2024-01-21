package com.example.food.presentation.screen.register

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.food.R
import com.example.food.databinding.FragmentRegisterBinding
import com.example.food.presentation.common.base.BaseFragment
import com.example.food.presentation.common.helper.Listener
import com.example.food.presentation.common.helper.Observer
import com.example.food.presentation.event.register.RegisterFragmentEvents
import com.example.food.presentation.event.register.RegisterNavigationEvents
import com.example.food.presentation.extension.showSnackbar
import com.example.food.presentation.state.register.RegisterViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate),
    Listener, Observer {

    private val viewModel: RegisterFragmentViewModel by viewModels()

    override fun init() {
        listeners()
        observers()
    }

    override fun listeners() {
        binding.buttonSignup.setOnClickListener {
            register()
        }

    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registerState.collect {
                    handleRegisterState(it!!)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleNavigationEvents(it)
                }
            }
        }
    }

    private fun register() {
        viewModel.onEvent(
            RegisterFragmentEvents.Register(
                username = binding.etUsername.text.toString(),
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString(),
                repeatPassword = binding.etRetypePassword.text.toString()
            )
        )
    }

    private fun handleRegisterState(registerViewState: RegisterViewState) {
        binding.progressBar.isVisible = registerViewState.isLoading

        registerViewState.errorMessage?.let {
            binding.root.showSnackbar(it)
            viewModel.onEvent(RegisterFragmentEvents.ResetErrorMessage)
        }
    }

    private fun handleNavigationEvents(event: RegisterNavigationEvents) {
        when (event) {
            is RegisterNavigationEvents.NavigateToLogin -> findNavController().navigate(
                R.id.action_registerFragment_to_loginFragment
            )
        }
    }

}