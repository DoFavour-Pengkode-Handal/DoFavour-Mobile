package com.example.dofavour.android.di

import com.example.dofavour.android.core.data.preferences.EncTokenPreferences
import com.example.dofavour.core.data.remote.TokenPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EncTokenPreferenceModule {

    @Binds
    abstract fun providePreferences(
        preferences: EncTokenPreferences
    ): TokenPreferences
}