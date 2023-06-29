package com.example.dofavour.landing.presentation.reset_password

sealed class ResetPasswordEvent {
    data class OnEmailChange(val email: String): ResetPasswordEvent()
    data class OnPasswordChange(val password: String): ResetPasswordEvent()
    object ToggleShowPassword: ResetPasswordEvent()
    object Reset: ResetPasswordEvent()
}
