package com.example.dofavour.android.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dofavour.home.domain.use_cases.SearchCampaigns
import com.example.dofavour.home.presentation.HomeEvent
import com.example.dofavour.home.presentation.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidHomeViewModel @Inject constructor(
    private val searchCampaigns: SearchCampaigns
): ViewModel() {
    private val viewModel by lazy {
        HomeViewModel(
            searchCampaigns = searchCampaigns,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: HomeEvent) {
        viewModel.onEvent(event)
    }
}