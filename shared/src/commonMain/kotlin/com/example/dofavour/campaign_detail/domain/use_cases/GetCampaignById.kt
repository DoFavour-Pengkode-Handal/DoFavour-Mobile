package com.example.dofavour.campaign_detail.domain.use_cases

import com.example.dofavour.core.utils.Resource
import com.example.dofavour.home.presentation.Campaign
import com.example.dofavour.home.presentation.HomeDummy

class GetCampaignById {
    operator fun invoke(
        campaignId: String
    ): Resource<Campaign> {
        val campaign = HomeDummy.campaigns.firstOrNull { it.id == campaignId }

        return campaign?.let {
            Resource.Success(it)
        } ?: Resource.Error("Campaign Not Found")
    }
}