package com.example.dofavour.landing.data.remote.source

import com.example.dofavour.core.data.utils.ApiResponse
import com.example.dofavour.core.data.utils.tryCatch
import com.example.dofavour.core.domain.dispatchers.DispatchersProvider
import com.example.dofavour.landing.data.remote.dto.request.LoginRequest
import com.example.dofavour.landing.data.remote.dto.request.RegisterRequest
import com.example.dofavour.landing.data.remote.dto.response.LoginResponse
import com.example.dofavour.landing.data.remote.service.LandingApiService
import kotlinx.coroutines.withContext

class LandingRemoteDataSource(
    private val apiService: LandingApiService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun login(
        request: LoginRequest
    ): ApiResponse<LoginResponse> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.login(request)

                if (result.status != "failed") {
                    ApiResponse.Success(result)
                }
                else {
                    ApiResponse.Error(result.message ?: "Unexpected Error")
                }
            }
        }
    }

    suspend fun register(
        request: RegisterRequest
    ): ApiResponse<Unit> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.register(request)

                if (result.status != "failed") {
                    ApiResponse.Success(Unit)
                }
                else {
                    ApiResponse.Error(result.message)
                }
            }
        }
    }
}