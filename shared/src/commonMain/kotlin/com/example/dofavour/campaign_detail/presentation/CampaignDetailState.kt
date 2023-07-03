package com.example.dofavour.campaign_detail.presentation

import com.example.dofavour.home.presentation.Campaign

data class CampaignDetailState(
    val campaign: Campaign? = null,
    val participants: List<Volunteer> = emptyList(),
    val joinSuccess: Boolean = false
)

data class Volunteer(
    val id: String,
    val name: String,
    val imageUrl: String
)

object CampaignDummy {
    val participants = listOf(
        Volunteer(
            id = "V1",
            name = "Darren",
            imageUrl = ""
        ),
        Volunteer(
            id = "V2",
            name = "Vincent",
            imageUrl = ""
        ),
        Volunteer(
            id = "V3",
            name = "Daffa",
            imageUrl = ""
        )
    )
}