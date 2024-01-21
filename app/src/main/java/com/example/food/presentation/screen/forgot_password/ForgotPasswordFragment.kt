package com.example.food.presentation.screen.forgot_password

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.food.databinding.FragmentForgotPasswordBinding
import com.example.food.presentation.common.base.BaseFragment
import com.example.food.presentation.common.helper.Listener
import com.example.food.presentation.common.helper.Observer
import com.example.food.presentation.event.forgot_password.ForgotPasswordEvents
import com.example.food.presentation.extension.showSnackbar
import com.example.food.presentation.state.forgot_password.ForgotPasswordViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgotPasswordFragment :
    BaseFragment<FragmentForgotPasswordBinding>(FragmentForgotPasswordBinding::inflate), Observer,
    Listener {

    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun init() {
        listeners()
        observers()
    }

    override fun listeners() {
        binding.buttonSend.setOnClickListener {
            sendEmail()
        }
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.forgotPasswordState.collect {
                    handleForgotPasswordState(it)
                }
            }
        }
    }

    private fun sendEmail() {
        viewModel.onEvent(
            ForgotPasswordEvents.SendEmail(
                email = binding.etEmail.text.toString()
            )
        )
    }

    private fun handleForgotPasswordState(forgotPasswordViewState: ForgotPasswordViewState) {
        binding.progressBar.isVisible = forgotPasswordViewState.isLoading

        forgotPasswordViewState.errorMessage?.let {
            binding.root.showSnackbar(it)
            viewModel.onEvent(ForgotPasswordEvents.ResetErrorMessage)
        }
    }
}