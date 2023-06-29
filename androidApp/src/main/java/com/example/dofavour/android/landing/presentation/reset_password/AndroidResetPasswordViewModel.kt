package com.example.dofavour.android.landing.presentation.reset_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dofavour.landing.domain.use_cases.ValidateEmail
import com.example.dofavour.landing.domain.use_cases.ValidatePassword
import com.example.dofavour.landing.presentation.reset_password.ResetPasswordEvent
import com.example.dofavour.landing.presentation.reset_password.ResetPasswordViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidResetPasswordViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
): ViewModel() {
    private val viewModel by lazy {
        ResetPasswordViewModel(
            validateEmail = validateEmail,
            validatePassword = validatePassword,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent

    fun onEvent(event: ResetPasswordEvent) {
        viewModel.onEvent(event)
    }
}