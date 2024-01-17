package com.example.foodapplication.presentation.screen.log_in

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.foodapplication.databinding.FragmentLoginBinding
import com.example.foodapplication.presentation.common.base.BaseFragment
import com.example.foodapplication.presentation.common.helper.Listener
import com.example.foodapplication.presentation.common.helper.Observer
import com.example.foodapplication.presentation.event.log_in.LoginFragmentEvents
import com.example.foodapplication.presentation.event.log_in.LoginNavigationEvents
import com.example.foodapplication.presentation.extension.showSnackbar
import com.example.foodapplication.presentation.state.log_in.LoginViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate), Observer,
    Listener {

    private val viewModel: LoginFragmentViewModel by viewModels()

    override fun init() {
        listeners()
        observers()
    }

    override fun listeners() {
        binding.buttonLogin.setOnClickListener {
            logIn()
        }

        binding.tvSignup.setOnClickListener {
            handleNavigationEvents(LoginNavigationEvents.NavigateToRegister)
        }
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginState.collect {
                    handleLoginState(it!!)
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

    private fun logIn() {
        viewModel.onEvent(
            LoginFragmentEvents.Login(
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString()
            )
        )
    }

    private fun handleLoginState(loginViewState: LoginViewState) {
        binding.progressBar.isVisible = loginViewState.isLoading

        loginViewState.errorMessage?.let {
            binding.root.showSnackbar(it)
            viewModel.onEvent(LoginFragmentEvents.ResetErrorMessage)
        }
    }

    private fun handleNavigationEvents(event: LoginNavigationEvents) {
        when (event) {
            is LoginNavigationEvents.NavigateToHome -> {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHostFragment())
            }

            is LoginNavigationEvents.NavigateToRegister -> {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }
    }
}