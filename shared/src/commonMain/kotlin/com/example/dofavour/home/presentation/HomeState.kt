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
    val type: String,
    val location: String,
    val description: String
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
    private val description = "Our once-glistening river now suffers from pollution and neglect. Garbage lines the shores, and a pungent odor fills the air. Fish and wildlife struggle to survive. But there's hope – join our volunteer cleanup event! Let's restore the river's beauty and protect its ecosystem. Together, we can make a difference for a cleaner, healthier environment. Mark your calendars and be part of the change. Join us to clean up our river and leave a positive legacy for future generations. Let's act now for a better tomorrow!"

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
            type = CampaignFilter.Nature.name,
            location = "",
            description = description
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
            type = CampaignFilter.Nature.name,
            location = "",
            description = description
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
            type = CampaignFilter.Nature.name,
            location = "",
            description = description
        )
    )
}