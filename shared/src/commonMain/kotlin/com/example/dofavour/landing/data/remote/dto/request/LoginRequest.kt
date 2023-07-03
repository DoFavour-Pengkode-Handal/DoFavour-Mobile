package com.example.dofavour.landing.data.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val traits: List<Trait>,
    val method: String,
    val password: String,
    val password_identifier: String
) {
    @Serializable
    data class Trait(
        val email: String
    )
}
