package com.example.dofavour.landing.presentation.register

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

class RegisterViewModel(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    fun onEvent(event: RegisterEvent) {
        when(event) {
            RegisterEvent.Register -> {
                _state.value = state.value.copy(
                    registerSuccess = true
                )

                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Success
                    )
                }
            }
            is RegisterEvent.OnFulNameChange -> {
                _state.value = state.value.copy(
                    fullName = event.name,
                    registerError = null
                )
            }
            is RegisterEvent.OnEmailChange -> {
                _state.value = state.value.copy(
                    email = event.email,
                    registerError = null
                )

                val isValid = validateEmail(email = event.email)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        emailError = null
                    )

                    _state.value = state.value.copy(
                        registerButtonEnabled = isButtonEnabled()
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        emailError = isValid.exceptionOrNull() as? ValidationError
                    )

                    _state.value = state.value.copy(
                        registerButtonEnabled = isButtonEnabled()
                    )
                }
            }
            is RegisterEvent.OnPasswordChange -> {
                _state.value = state.value.copy(
                    password = event.password,
                    registerError = null
                )

                val isValid = validatePassword(password = event.password)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        passwordError = null
                    )

                    _state.value = state.value.copy(
                        registerButtonEnabled = isButtonEnabled()
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        passwordError = isValid.exceptionOrNull() as? ValidationError
                    )

                    _state.value = state.value.copy(
                        registerButtonEnabled = isButtonEnabled()
                    )
                }
            }
            RegisterEvent.ToggleShowPassword -> {
                _state.value = state.value.copy(
                    isPasswordVisible = !state.value.isPasswordVisible
                )
            }
        }
    }

    private fun isButtonEnabled(): Boolean {
        return state.value.email.isNotEmpty()
                && state.value.emailError == null
                && state.value.fullName.isNotEmpty()
                && state.value.fullNameError == null
                && state.value.password.isNotEmpty()
                && state.value.passwordError == null
                && state.value.registerError == null
    }
}