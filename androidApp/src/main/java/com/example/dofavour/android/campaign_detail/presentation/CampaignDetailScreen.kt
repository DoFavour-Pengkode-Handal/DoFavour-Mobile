package com.example.dofavour.android.campaign_detail.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dofavour.android.R
import com.example.dofavour.android.core_ui.CommonHeader
import com.example.dofavour.android.core_ui.DefaultButton
import com.example.dofavour.android.core_ui.theme.DoFavourTheme
import com.example.dofavour.android.home.presentation.components.CampaignBar
import com.example.dofavour.campaign_detail.presentation.CampaignDetailEvent
import com.example.dofavour.campaign_detail.presentation.CampaignDetailState

@Composable
fun CampaignDetailScreen(
    state: CampaignDetailState,
    onEvent: (CampaignDetailEvent) -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val campaign = state.campaign
    val organization = campaign?.organization

    Scaffold(
        topBar = {
            CommonHeader(
                title = stringResource(id = R.string.details),
                onBackClick = onBackClick
            )
        },
        floatingActionButton = {
            DefaultButton(
                text = stringResource(id = R.string.join),
                textColor = MaterialTheme.colors.onBackground,
                onClick = {
                    onEvent(CampaignDetailEvent.Join)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                    model = ImageRequest
                        .Builder(context)
                        .data(campaign?.imageUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.ic_no_picture),
                    contentDescription = campaign?.id + " photo"
                )
            }

            item {
                Text(
                    text = campaign?.name ?: stringResource(id = R.string.loading_placeholder),
                    style = MaterialTheme.typography.h4
                )
                
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.campaign_created_by) + " ",
                        style = MaterialTheme.typography.body1.copy(
                            color = Color.Gray
                        )
                    )

                    Text(
                        text = organization?.name ?: stringResource(id = R.string.loading_placeholder),
                        style = MaterialTheme.typography.body1
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(
                        imageVector = Icons.Filled.Verified,
                        contentDescription = null
                    )
                }
            }

            item {
                CampaignBar(
                    total = campaign?.volunteerCount ?: 0,
                    limit = campaign?.limit ?: 1
                )

                Text(
                    text = stringResource(id = R.string.campaign_count,
                        ((campaign?.limit ?: 0) - (campaign?.volunteerCount ?: 0)).toString(),
                        (campaign?.limit ?: 0).toString()
                    ),
                    style = MaterialTheme.typography.body1
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.description),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = campaign?.description ?: stringResource(id = R.string.loading_placeholder),
                    style = MaterialTheme.typography.body1.copy(
                        color = Color.Gray
                    )
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.description),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(32.dp),
                        imageVector = Icons.Rounded.CalendarMonth,
                        contentDescription = "Date"
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = campaign?.date ?: stringResource(id = R.string.loading_placeholder),
                            style = MaterialTheme.typography.body1
                        )

                        Text(
                            text = campaign?.time ?: stringResource(id = R.string.loading_placeholder),
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }

            item {
                Text(
                    text = stringResource(id = R.string.location),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(32.dp),
                        imageVector = Icons.Rounded.LocationOn,
                        contentDescription = "Date"
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = campaign?.location ?: stringResource(id = R.string.loading_placeholder),
                        style = MaterialTheme.typography.body1
                    )
                }
            }

            item {
                Text(
                    text = stringResource(id = R.string.participants),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    state
                        .participants
                        .take(5)
                        .forEach { participant ->
                            AsyncImage(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop,
                                model = ImageRequest
                                    .Builder(context)
                                    .data(participant.imageUrl)
                                    .crossfade(true)
                                    .build(),
                                placeholder = painterResource(id = R.drawable.ic_no_picture),
                                contentDescription = participant.id + " photo"
                            )
                        }
                }

                if (state.participants.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.no_participants),
                        style = MaterialTheme.typography.body1.copy(
                            color = Color.Gray
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CampaignDetailScreenPreview() {
    DoFavourTheme {
        CampaignDetailScreen(
            state = CampaignDetailState(),
            onEvent = {  },
            onBackClick = {  }
        )
    }
}