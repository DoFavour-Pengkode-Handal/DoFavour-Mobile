package com.example.dofavour.android.di

import com.example.dofavour.core.domain.dispatchers.DispatchersProvider
import com.example.dofavour.core.domain.dispatchers.StandardDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDispatchers(): DispatchersProvider {
        return StandardDispatchers()
    }
}