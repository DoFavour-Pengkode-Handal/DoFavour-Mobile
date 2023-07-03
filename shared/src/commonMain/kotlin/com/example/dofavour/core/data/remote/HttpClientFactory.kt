package com.example.dofavour.core.data.remote

import io.ktor.client.*

expect class HttpClientFactory {
    fun create(tokenPreferences: TokenPreferences): HttpClient
}