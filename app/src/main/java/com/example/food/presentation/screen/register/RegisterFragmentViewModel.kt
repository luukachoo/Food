package com.example.food.presentation.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food.data.common.Resource
import com.example.food.data.remote.network.model.UserInfo
import com.example.food.domain.remote.use_case.user.UserUseCase
import com.example.food.domain.remote.use_case.validator.ValidatorUseCase
import com.example.food.presentation.event.register.RegisterFragmentEvents
import com.example.food.presentation.event.register.RegisterNavigationEvents
import com.example.food.presentation.state.register.RegisterViewState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val validator: ValidatorUseCase
) : ViewModel() {

    private val _registerState = MutableStateFlow(RegisterViewState(null))
    val registerState: StateFlow<RegisterViewState?> = _registerState

    private val _uiEvent = MutableSharedFlow<RegisterNavigationEvents>()
    val uiEvent: SharedFlow<RegisterNavigationEvents> get() = _uiEvent


    fun onEvent(event: RegisterFragmentEvents) {
        when (event) {
            is RegisterFragmentEvents.Register -> validateForm(
                event.username,
                event.email,
                event.password,
                event.repeatPassword
            )

            is RegisterFragmentEvents.ResetErrorMessage -> updateErrorMessage(null)
        }
    }

    private fun register(
        username: String,
        email: String,
        password: String,
        userInfo: UserInfo = UserInfo()
    ) =
        viewModelScope.launch {
            _registerState.update { it.copy(isLoading = true) }
            userUseCase.register(email, password, userInfo).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _registerState.update {
                            it.copy(
                                resource = resource,
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                        saveUserInfoToFirebase(username, email, password)
                        _uiEvent.emit(RegisterNavigationEvents.NavigateToLogin)
                    }

                    is Resource.Failure -> {
                        _registerState.update {
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

    private fun saveUserInfoToFirebase(username: String, email: String, password: String) {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val userInfo = UserInfo(username, email, password)
            val databaseReference =
                FirebaseDatabase.getInstance().getReference("/users/${user.uid}")
            databaseReference.setValue(userInfo)
                .addOnFailureListener { e ->
                    _registerState.update {
                        it.copy(
                            errorMessage = e.message
                        )
                    }
                }
        }
    }

    private fun validateForm(
        username: String,
        email: String,
        password: String,
        repeatPassword: String
    ) {
        val isEmailValid = validator.emailValidator(email)
        val isPasswordValid = validator.passwordValidator(password)
        val isUsernameValid = validator.usernameValidator(username)
        val doPasswordsMatch = validator.passwordsMatchValidator(password, repeatPassword)
        val areFieldsValid =
            listOf(isEmailValid, isPasswordValid, isUsernameValid, doPasswordsMatch).all { it }

        if (!areFieldsValid) {
            updateErrorMessage(message = "Fields are not valid!")
            return
        }
        register(username, email, password)
    }

    private fun updateErrorMessage(message: String?) {
        _registerState.update { currentState -> currentState.copy(errorMessage = message) }
    }

}