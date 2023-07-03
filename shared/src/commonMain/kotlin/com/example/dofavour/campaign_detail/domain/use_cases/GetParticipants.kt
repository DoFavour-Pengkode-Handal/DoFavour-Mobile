package com.example.dofavour.campaign_detail.domain.use_cases

import com.example.dofavour.campaign_detail.presentation.CampaignDummy
import com.example.dofavour.campaign_detail.presentation.Volunteer
import com.example.dofavour.core.utils.Resource

class GetParticipants {
    operator fun invoke(
        campaignId: String
    ): Resource<List<Volunteer>> {
        return Resource.Success(CampaignDummy.participants)
    }
}