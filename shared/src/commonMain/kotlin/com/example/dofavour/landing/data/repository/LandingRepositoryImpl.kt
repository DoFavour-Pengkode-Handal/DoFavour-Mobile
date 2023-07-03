package com.example.dofavour.landing.data.repository

import com.example.dofavour.core.data.remote.TokenPreferences
import com.example.dofavour.core.data.utils.ApiResponse
import com.example.dofavour.core.utils.Resource
import com.example.dofavour.landing.data.remote.dto.request.LoginRequest
import com.example.dofavour.landing.data.remote.dto.request.RegisterRequest
import com.example.dofavour.landing.data.remote.source.LandingRemoteDataSource
import com.example.dofavour.landing.domain.repository.LandingRepository

class LandingRepositoryImpl(
    private val remoteDataSource: LandingRemoteDataSource,
    private val tokenPreferences: TokenPreferences
): LandingRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Resource<Unit> {
        val request = LoginRequest(
            traits = listOf(
                LoginRequest.Trait(
                    email = email
                )
            ),
            method = "password",
            password = password,
            password_identifier = "email"
        )

        return when(
            val result = remoteDataSource.login(request)
        ) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                tokenPreferences.setToken(
                    accessToken = result.data.access_token ?: "",
                    refreshToken = result.data.refresh_token ?: "",
                )

                Resource.Success(Unit)
            }
        }
    }

    override suspend fun register(email: String, password: String): Resource<Unit> {
        val request = RegisterRequest(
            traits = listOf(
                RegisterRequest.Trait(
                    email = email
                )
            ),
            method = "password",
            password = password
        )

        return when(
            val result = remoteDataSource.register(request)
        ) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(Unit)
            }
        }
    }
}