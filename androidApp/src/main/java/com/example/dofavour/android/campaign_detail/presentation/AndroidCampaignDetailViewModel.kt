package com.example.dofavour.android.campaign_detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dofavour.campaign_detail.domain.use_cases.GetCampaignById
import com.example.dofavour.campaign_detail.domain.use_cases.GetParticipants
import com.example.dofavour.campaign_detail.presentation.CampaignDetailEvent
import com.example.dofavour.campaign_detail.presentation.CampaignDetailViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidCampaignDetailViewModel @Inject constructor(
    private val getCampaignById: GetCampaignById,
    private val getParticipants: GetParticipants,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val viewModel by lazy {
        CampaignDetailViewModel(
            getCampaignById = getCampaignById,
            getParticipants = getParticipants,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state
    val uiEvent = viewModel
        .uiEvent

    fun onEvent(event: CampaignDetailEvent) {
        viewModel.onEvent(event)
    }

    init {
        val campaignId = savedStateHandle.get<String>("campaign_id")

        campaignId?.let {
            viewModel.init(it)
        }
    }
}