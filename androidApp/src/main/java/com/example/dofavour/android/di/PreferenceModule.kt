package com.example.dofavour.android.di

import com.example.dofavour.android.core.data.preferences.DefaultPreferences
import com.example.dofavour.android.core.domain.preferences.Preferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Binds
    abstract fun providePreferences(
        preferences: DefaultPreferences
    ): Preferences
    
}