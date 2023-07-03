package com.example.dofavour.landing.domain.use_cases

data class LandingUseCases(
    val validateNumber: ValidateNumber,
    val validatePassword: ValidatePassword,
    val validateEmail: ValidateEmail,
    val login: Login,
    val register: Register
)
