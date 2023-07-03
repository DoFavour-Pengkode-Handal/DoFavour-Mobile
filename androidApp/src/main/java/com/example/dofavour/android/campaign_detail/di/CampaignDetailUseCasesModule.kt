package com.example.dofavour.android.campaign_detail.di

import com.example.dofavour.campaign_detail.domain.use_cases.GetCampaignById
import com.example.dofavour.campaign_detail.domain.use_cases.GetParticipants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CampaignDetailUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetCampaignById(): GetCampaignById {
        return GetCampaignById()
    }

    @Provides
    @ViewModelScoped
    fun provideGetParticipants(): GetParticipants {
        return GetParticipants()
    }
}