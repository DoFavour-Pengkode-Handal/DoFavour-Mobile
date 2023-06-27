package com.example.dofavour.android.landing.presentation.verify_otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dofavour.landing.presentation.verify_otp.VerifyOtpEvent
import com.example.dofavour.landing.presentation.verify_otp.VerifyOtpViewModel

class AndroidVerifyOtpViewModel: ViewModel() {
    private val viewModel by lazy {
        VerifyOtpViewModel(
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent

    fun onEvent(event: VerifyOtpEvent) {
        viewModel.onEvent(event)
    }
}