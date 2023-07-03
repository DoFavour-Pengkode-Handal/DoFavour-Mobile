package com.example.dofavour.campaign_detail.presentation

import com.example.dofavour.campaign_detail.domain.use_cases.GetCampaignById
import com.example.dofavour.campaign_detail.domain.use_cases.GetParticipants
import com.example.dofavour.core.domain.utils.toCommonFlow
import com.example.dofavour.core.domain.utils.toCommonStateFlow
import com.example.dofavour.core.utils.Resource
import com.example.dofavour.core.utils.UiEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CampaignDetailViewModel(
    private val getCampaignById: GetCampaignById,
    private val getParticipants: GetParticipants,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(CampaignDetailState())
    val state = _state.toCommonStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent
        .receiveAsFlow()
        .toCommonFlow()

    fun onEvent(event: CampaignDetailEvent) {
        when(event) {
            CampaignDetailEvent.Join -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Success)

                    _state.value = state.value.copy(
                        joinSuccess = true
                    )
                }
            }
            is CampaignDetailEvent.UpdateResult -> {
                _state.value = state.value.copy(
                    joinSuccess = event.result
                )
            }
        }
    }

    fun init(campaignId: String) {
        viewModelScope.launch {
            val result = getCampaignById(
                campaignId = campaignId
            )

            when(result) {
                is Resource.Error -> {

                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        campaign = result.data
                    )
                }
            }
        }

        viewModelScope.launch {
            val result = getParticipants(
                campaignId = campaignId
            )

            when(result) {
                is Resource.Error -> {

                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        participants = result.data ?: emptyList()
                    )
                }
            }
        }
    }
}