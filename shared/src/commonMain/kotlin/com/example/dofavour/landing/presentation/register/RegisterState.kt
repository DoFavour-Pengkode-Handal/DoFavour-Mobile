package com.example.dofavour.landing.presentation.register

import com.example.ajarin.core.utils.erros.ValidationError

data class RegisterState(
    val fullName: String = "",
    val fullNameError: ValidationError? = null,
    val email: String = "",
    val emailError: ValidationError? = null,
    val password: String = "",
    val passwordError: ValidationError? = null,
    val isPasswordVisible: Boolean = false,
    val registerButtonEnabled: Boolean = false,
    val registerError: String? = "",
    val registerSuccess: Boolean = false
)