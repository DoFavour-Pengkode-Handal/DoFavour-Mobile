package com.example.dofavour.landing.presentation.verify_otp

import com.example.ajarin.core.utils.erros.ValidationError

data class VerifyOtpState(
    val otp: String = "",
    val otpError: ValidationError? = null,
    val otpSendCount: Int = 0,
    val otpCountDown: Int = 0,
    val sendOtpError: String? = null,
    val sendOtpLoading: Boolean = false,
    val verifyOtpSuccess: Boolean = false,
    val verifyOtpError: String? = null,
    val verifyOtpLoading: Boolean = false
)
