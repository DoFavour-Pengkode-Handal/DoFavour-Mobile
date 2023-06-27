package com.example.dofavour.landing.presentation.verify_otp

import com.example.dofavour.core.domain.utils.toCommonFlow
import com.example.dofavour.core.domain.utils.toCommonStateFlow
import com.example.dofavour.core.utils.UiEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class VerifyOtpViewModel(
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(VerifyOtpState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    private var countDownJob: Job? = null

    fun onEvent(event: VerifyOtpEvent) {
        when(event) {
            is VerifyOtpEvent.OnOtpChange -> {
                _state.value = state.value.copy(
                    otp = event.otp,
                    verifyOtpError = null
                )
            }
            VerifyOtpEvent.OnVerifyOtp -> {
                _state.value = state.value.copy(
                    verifyOtpSuccess = true
                )

                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Success
                    )
                }
            }
            VerifyOtpEvent.ReSendOtp -> {
                val sendCount = state.value.otpSendCount + 1
                val countDown = sendCount * sendCount * 30

                _state.value = state.value.copy(
                    otpSendCount = sendCount,
                    otpCountDown = countDown
                )

                countDown()
            }
        }
    }

    private fun countDown() {
        countDownJob?.cancel()
        countDownJob = viewModelScope.launch {
            (state.value.otpCountDown - 1 downTo 0).asFlow()
                .onEach {
                    delay(1000)
                }
                .conflate()
                .collect {
                    _state.value = state.value.copy(
                        otpCountDown = it
                    )
                }
        }
    }
}