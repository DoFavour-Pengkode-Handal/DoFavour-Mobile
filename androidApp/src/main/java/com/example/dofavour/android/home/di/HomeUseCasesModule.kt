package com.example.dofavour.android.home.di

import com.example.dofavour.home.domain.use_cases.SearchCampaigns
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object HomeUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideSearchCampaignsUseCase(): SearchCampaigns {
        return SearchCampaigns()
    }
}