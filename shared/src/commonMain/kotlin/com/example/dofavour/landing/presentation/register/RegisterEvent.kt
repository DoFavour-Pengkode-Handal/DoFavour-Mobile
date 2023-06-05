package com.example.dofavour.landing.presentation.register

sealed class RegisterEvent {
    data class OnFulNameChange(val name: String): RegisterEvent()
    data class OnEmailChange(val email: String): RegisterEvent()
    data class OnPasswordChange(val password: String): RegisterEvent()
    object ToggleShowPassword: RegisterEvent()
    object Register: RegisterEvent()
}