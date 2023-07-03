package com.example.dofavour.home.domain.use_cases

import com.example.dofavour.core.utils.Resource
import com.example.dofavour.home.presentation.Campaign
import com.example.dofavour.home.presentation.CampaignFilter
import com.example.dofavour.home.presentation.HomeDummy

class SearchCampaigns {
    operator fun invoke(
        searchText: String,
        filter: CampaignFilter
    ): Resource<List<Campaign>> {
        if(filter == CampaignFilter.All) {
            return Resource.Success(
                HomeDummy
                    .campaigns
                    .filter { it.name.contains(searchText) }
            )
        }

        return Resource.Success(
            HomeDummy
                .campaigns
                .filter { it.name.contains(searchText) }
                .filter { it.type == filter.name }
        )
    }
}