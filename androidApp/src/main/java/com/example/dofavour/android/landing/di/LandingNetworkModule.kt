package com.example.dofavour.android.landing.di

import com.example.dofavour.core.domain.dispatchers.DispatchersProvider
import com.example.dofavour.landing.data.remote.service.KtorLandingApiService
import com.example.dofavour.landing.data.remote.service.LandingApiService
import com.example.dofavour.landing.data.remote.source.LandingRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LandingNetworkModule {

    @Provides
    @Singleton
    fun provideLandingApi(
        client: HttpClient
    ): LandingApiService {
        return KtorLandingApiService(client = client)
    }

    @Provides
    @Singleton
    fun provideLandingRemoteDataSource(
        landingApi: LandingApiService,
        dispatchers: DispatchersProvider
    ): LandingRemoteDataSource {
        return LandingRemoteDataSource(
            apiService = landingApi,
            dispatchers = dispatchers
        )
    }
}