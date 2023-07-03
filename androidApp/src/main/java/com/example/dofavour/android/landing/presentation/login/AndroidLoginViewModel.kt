package com.example.dofavour.android.landing.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dofavour.android.core.domain.preferences.Preferences
import com.example.dofavour.core.utils.UiEvent
import com.example.dofavour.landing.domain.use_cases.Login
import com.example.dofavour.landing.domain.use_cases.ValidateEmail
import com.example.dofavour.landing.domain.use_cases.ValidatePassword
import com.example.dofavour.landing.presentation.login.LoginEvent
import com.example.dofavour.landing.presentation.login.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AndroidLoginViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val login: Login,
    private val preferences: Preferences
): ViewModel() {
    private val viewModel by lazy {
        LoginViewModel(
            validateEmail = validateEmail,
            validatePassword = validatePassword,
            login = login,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent
        .onEach {
            if (it == UiEvent.Success) {
                preferences.saveShouldShowOnBoarding(
                    shouldShow = false
                )
            }
        }

    fun onEvent(event: LoginEvent) {
        viewModel.onEvent(event)
    }
}