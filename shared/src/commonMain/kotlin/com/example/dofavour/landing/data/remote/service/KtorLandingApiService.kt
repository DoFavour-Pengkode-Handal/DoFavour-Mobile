package com.example.dofavour.landing.data.remote.service

import com.example.dofavour.landing.data.remote.dto.request.LoginRequest
import com.example.dofavour.landing.data.remote.dto.request.RegisterRequest
import com.example.dofavour.landing.data.remote.dto.response.LoginResponse
import com.example.dofavour.landing.data.remote.dto.response.RegisterResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class KtorLandingApiService(
    private val client: HttpClient
): LandingApiService {
    override suspend fun login(request: LoginRequest): LoginResponse {
        val result = client.post {
            url(LOGIN_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

    override suspend fun register(request: RegisterRequest): RegisterResponse {
        val result = client.post {
            url(REGISTER_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

    companion object {
        private const val BASE_URL = "http://192.168.8.140:4334"
        private const val LOGIN_URL = "$BASE_URL/local/login"
        private const val REGISTER_URL = "$BASE_URL/local/register"
    }
}