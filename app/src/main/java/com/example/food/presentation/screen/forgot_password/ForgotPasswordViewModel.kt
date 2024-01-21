package com.example.food.presentation.screen.forgot_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food.data.common.Resource
import com.example.food.domain.remote.use_case.user.ForgotPasswordUseCase
import com.example.food.domain.remote.use_case.validator.EmailValidatorUseCase
import com.example.food.presentation.event.forgot_password.ForgotPasswordEvents
import com.example.food.presentation.state.forgot_password.ForgotPasswordViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val emailValidator: EmailValidatorUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {

    private val _forgotPasswordState = MutableStateFlow(ForgotPasswordViewState(null))
    val forgotPasswordState get() = _forgotPasswordState

    fun onEvent(event: ForgotPasswordEvents) {
        when (event) {
            is ForgotPasswordEvents.SendEmail -> validateEmail(event.email)
            is ForgotPasswordEvents.ResetErrorMessage -> updateErrorMessage(null)
        }
    }

    private fun forgotPassword(email: String) = viewModelScope.launch {
        _forgotPasswordState.update { it.copy(isLoading = true) }
        forgotPasswordUseCase(email).collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    _forgotPasswordState.update {
                        it.copy(
                            resource = resource,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }

                is Resource.Failure -> {
                    _forgotPasswordState.update {
                        it.copy(
                            resource = null,
                            isLoading = false,
                            errorMessage = resource.errorMessage
                        )
                    }
                }
            }
        }
    }

    private fun validateEmail(email: String) {
        val isEmailValid = emailValidator(email)
        if (!isEmailValid) {
            updateErrorMessage(message = "Field is not valid!")
            return
        }
        forgotPassword(email)
    }

    private fun updateErrorMessage(message: String?) {
        _forgotPasswordState.update { currentState -> currentState.copy(errorMessage = message) }
    }
}