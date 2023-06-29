package com.example.dofavour.landing.presentation.reset_password

import com.example.ajarin.core.utils.erros.ValidationError
import com.example.dofavour.core.domain.utils.toCommonFlow
import com.example.dofavour.core.domain.utils.toCommonStateFlow
import com.example.dofavour.core.utils.UiEvent
import com.example.dofavour.landing.domain.use_cases.ValidateEmail
import com.example.dofavour.landing.domain.use_cases.ValidatePassword
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ResetPasswordViewModel(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(ResetPasswordState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    fun onEvent(event: ResetPasswordEvent) {
        when (event) {
            is ResetPasswordEvent.OnEmailChange -> {
                _state.value = state.value.copy(
                    email = event.email,
                    resetError = null
                )

                val isValid = validateEmail(email = event.email)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        emailError = null
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        emailError = isValid.exceptionOrNull() as? ValidationError
                    )
                }
            }
            is ResetPasswordEvent.OnPasswordChange -> {
                _state.value = state.value.copy(
                    password = event.password,
                    resetError = null
                )

                val isValid = validatePassword(password = event.password)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        passwordError = null
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        passwordError = isValid.exceptionOrNull() as? ValidationError
                    )
                }
            }
            ResetPasswordEvent.Reset -> {
                _state.value = state.value.copy(
                    resetSuccess = true
                )

                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Success
                    )
                }
            }
            ResetPasswordEvent.ToggleShowPassword -> {
                _state.value = state.value.copy(
                    isPasswordVisible = !state.value.isPasswordVisible
                )
            }
        }
    }
}