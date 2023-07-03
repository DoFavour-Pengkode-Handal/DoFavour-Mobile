package com.example.dofavour.landing.data.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val access_token: String? = null,
    val refresh_token: String? = null,
    val status: String? = null,
    val code: Int? = null,
    val codeStatus: String? = null,
    val message: String? = null
)
