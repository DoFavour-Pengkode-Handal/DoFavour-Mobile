package com.example.dofavour.android.landing.di

import com.example.dofavour.core.data.remote.TokenPreferences
import com.example.dofavour.landing.data.remote.source.LandingRemoteDataSource
import com.example.dofavour.landing.data.repository.LandingRepositoryImpl
import com.example.dofavour.landing.domain.repository.LandingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LandingRepositoryModule {

    @Provides
    @Singleton
    fun provideLandingRepository(
        remoteDataSource: LandingRemoteDataSource,
        tokenPreferences: TokenPreferences
    ): LandingRepository {
        return LandingRepositoryImpl(
            remoteDataSource = remoteDataSource,
            tokenPreferences = tokenPreferences
        )
    }
}