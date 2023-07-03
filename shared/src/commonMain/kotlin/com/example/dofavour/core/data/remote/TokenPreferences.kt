package com.example.dofavour.core.data.remote

interface TokenPreferences {
    fun getAccessToken(): String

    fun getRefreshToken(): String

    fun setToken(
        accessToken: String,
        refreshToken: String
    )

    fun resetToken()
}