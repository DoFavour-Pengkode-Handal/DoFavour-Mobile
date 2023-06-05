package com.example.dofavour.android.landing.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dofavour.android.core.domain.preferences.Preferences
import com.example.dofavour.core.utils.UiEvent
import com.example.dofavour.landing.domain.use_cases.ValidateEmail
import com.example.dofavour.landing.domain.use_cases.ValidatePassword
import com.example.dofavour.landing.presentation.register.RegisterEvent
import com.example.dofavour.landing.presentation.register.RegisterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AndroidRegisterViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val preferences: Preferences
): ViewModel() {
    private val viewModel by lazy {
        RegisterViewModel(
            validateEmail = validateEmail,
            validatePassword = validatePassword,
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

    fun onEvent(event: RegisterEvent) {
        viewModel.onEvent(event)
    }
}