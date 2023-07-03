package com.example.dofavour.landing.data.remote.service

import com.example.dofavour.landing.data.remote.dto.request.LoginRequest
import com.example.dofavour.landing.data.remote.dto.request.RegisterRequest
import com.example.dofavour.landing.data.remote.dto.response.LoginResponse
import com.example.dofavour.landing.data.remote.dto.response.RegisterResponse

interface LandingApiService {
    suspend fun login(request: LoginRequest): LoginResponse
    suspend fun register(request: RegisterRequest): RegisterResponse
}