package com.example.dofavour.landing.data.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val status: String,
    val code: Int,
    val codeStatus: String,
    val message: String
)