package com.example.dofavour.home.presentation

data class HomeState(
    val searchText: String = "",
    val filter: CampaignFilter = CampaignFilter.All,
    val campaigns: List<Campaign> = emptyList()
)

data class Campaign(
    val id: String,
    val name: String,
    val organization: Organization,
    val date: String,
    val time: String,
    val limit: Int,
    val volunteerCount: Int,
    val imageUrl: String,
    val type: String
)

data class Organization(
    val id: String,
    val name: String,
    val dateCreated: String,
    val logo: String
)

enum class CampaignFilter {
    All,
    Nature,
    Health,
    Education
}

object HomeDummy {
    val organizations = listOf(
        Organization(
            id = "O1",
            name = "Cycle Community",
            dateCreated = "2015",
            logo = ""
        )
    )

    val campaigns = listOf(
        Campaign(
            id = "C1",
            name = "Clean Up River",
            organization = organizations[0],
            date = "3 July 2023",
            time = "07.00",
            limit = 1000,
            volunteerCount = 500,
            imageUrl = "",
            type = CampaignFilter.Nature.name
        ),
        Campaign(
            id = "C2",
            name = "Clean Up River",
            organization = organizations[0],
            date = "3 July 2023",
            time = "07.00",
            limit = 1000,
            volunteerCount = 500,
            imageUrl = "",
            type = CampaignFilter.Nature.name
        ),
        Campaign(
            id = "C3",
            name = "Clean Up River",
            organization = organizations[0],
            date = "3 July 2023",
            time = "07.00",
            limit = 1000,
            volunteerCount = 500,
            imageUrl = "",
            type = CampaignFilter.Nature.name
        )
    )
}