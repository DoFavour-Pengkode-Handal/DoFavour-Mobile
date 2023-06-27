package com.example.dofavour.landing.presentation.verify_otp

sealed class VerifyOtpEvent {
    data class OnOtpChange(val otp: String): VerifyOtpEvent()
    object ReSendOtp: VerifyOtpEvent()
    object OnVerifyOtp: VerifyOtpEvent()
}
