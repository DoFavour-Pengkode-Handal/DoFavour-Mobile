package com.example.dofavour.campaign_detail.presentation

sealed class CampaignDetailEvent {
    object Join: CampaignDetailEvent()
    data class UpdateResult(val result: Boolean): CampaignDetailEvent()
}
