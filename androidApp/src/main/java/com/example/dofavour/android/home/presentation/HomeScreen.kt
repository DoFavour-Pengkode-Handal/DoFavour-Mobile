package com.example.dofavour.android.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dofavour.android.core_ui.theme.DoFavourTheme
import com.example.dofavour.android.core_ui.theme.LocalGradient
import com.example.dofavour.android.home.presentation.components.CampaignItem
import com.example.dofavour.android.home.presentation.components.HomeHeader
import com.example.dofavour.home.presentation.HomeEvent
import com.example.dofavour.home.presentation.HomeState

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    onCampaignClick: (String) -> Unit
) {
    val localGradient = LocalGradient.current

    Scaffold(
        modifier = Modifier
            .background(localGradient.primary),
        topBar = {
            HomeHeader(
                state = state,
                onEvent = onEvent,
                onNotificationClick = {  }
            )
        },
        backgroundColor = Color.Transparent
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = state.campaigns,
                key = { campaign -> campaign.id }
            ) { campaign ->
                CampaignItem(
                    campaign = campaign,
                    onClick = {
                        onCampaignClick(campaign.id)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    DoFavourTheme {
        HomeScreen(
            state = HomeState(),
            onEvent = {  },
            onCampaignClick = {  }
        )
    }
}