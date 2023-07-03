package com.example.dofavour.home.presentation

sealed class HomeEvent {
    data class OnSearchTextChange(val newText: String): HomeEvent()
    data class SelectFilter(val filter: CampaignFilter): HomeEvent()
}
