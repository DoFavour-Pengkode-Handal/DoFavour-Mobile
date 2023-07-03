package com.example.dofavour.home.presentation

import com.example.dofavour.core.domain.utils.toCommonStateFlow
import com.example.dofavour.core.utils.Resource
import com.example.dofavour.home.domain.use_cases.SearchCampaigns
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val searchCampaigns: SearchCampaigns,
    coroutineScope: CoroutineScope? = null
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(HomeState())
    val state = _state.toCommonStateFlow()

    private var searchJob: Job? = null

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.SelectFilter -> {
                _state.value = state.value.copy(
                    filter = event.filter
                )

                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCampaigns()
                }
            }
            is HomeEvent.OnSearchTextChange -> {
                _state.value = state.value.copy(
                    searchText = event.newText
                )

                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCampaigns()
                }
            }
        }
    }

    private fun getCampaigns(
        query: String = state.value.searchText,
        filter: CampaignFilter = state.value.filter
    ) {
        viewModelScope.launch {
            val result = searchCampaigns(
                searchText = query,
                filter = filter
            )

            when(result) {
                is Resource.Error -> {

                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        campaigns = result.data ?: emptyList()
                    )
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            searchJob?.cancel()
            searchJob = viewModelScope.launch {
                getCampaigns()
            }
        }
    }
}