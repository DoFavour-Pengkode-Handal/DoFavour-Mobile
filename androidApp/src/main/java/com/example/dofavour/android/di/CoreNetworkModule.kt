package com.example.dofavour.android.di

import com.example.dofavour.core.data.remote.HttpClientFactory
import com.example.dofavour.core.data.remote.TokenPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {

    @Provides
    @Singleton
    fun provideClient(
        tokenPreferences: TokenPreferences
    ): HttpClient {
        return HttpClientFactory()
            .create(
                tokenPreferences = tokenPreferences
            )
    }
}