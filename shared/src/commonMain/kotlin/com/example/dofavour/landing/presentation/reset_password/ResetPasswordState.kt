package com.example.dofavour.landing.presentation.reset_password

import com.example.ajarin.core.utils.erros.ValidationError

data class ResetPasswordState(
    val email: String = "",
    val emailError: ValidationError? = null,
    val password: String = "",
    val passwordError: ValidationError? = null,
    val isPasswordVisible: Boolean = false,
    val isReset: Boolean = false,
    val resetError: String? = "",
    val resetSuccess: Boolean = false
)
