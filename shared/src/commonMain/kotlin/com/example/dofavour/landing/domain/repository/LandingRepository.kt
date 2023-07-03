package com.example.dofavour.landing.domain.repository

import com.example.dofavour.core.utils.Resource

interface LandingRepository {
    suspend fun login(
        email: String,
        password: String
    ): Resource<Unit>

    suspend fun register(
        email: String,
        password: String
    ): Resource<Unit>
}