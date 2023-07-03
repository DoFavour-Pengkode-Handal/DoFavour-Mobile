package com.example.dofavour.android.home.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Groups
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
import com.example.dofavour.android.core_ui.DefaultButton
import com.example.dofavour.android.core_ui.theme.DoFavourTheme
import com.example.dofavour.home.presentation.Campaign
import com.example.dofavour.home.presentation.HomeDummy

@Composable
fun CampaignItem(
    modifier: Modifier = Modifier,
    campaign: Campaign
) {
    val context = LocalContext.current
    val organization = campaign.organization

    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                    model = ImageRequest
                        .Builder(context)
                        .data(campaign.imageUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.ic_no_picture),
                    contentDescription = campaign.id + " photo"
                )

                DefaultButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    text = stringResource(id = R.string.view),
                    textColor = MaterialTheme.colors.onBackground,
                    onClick = {  }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = campaign.name,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = modifier
                        .size(32.dp),
                    contentScale = ContentScale.Fit,
                    model = ImageRequest
                        .Builder(context)
                        .data(campaign.imageUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.ic_no_picture),
                    contentDescription = campaign.id + " photo"
                )
                
                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.campaign_created_by) + " ",
                            style = MaterialTheme.typography.body1.copy(
                                color = Color.Gray
                            )
                        )

                        Text(
                            text = organization.name,
                            style = MaterialTheme.typography.body1
                        )
                    }

                    Text(
                        text = stringResource(id = R.string.campaign_verified, organization.dateCreated),
                        style = MaterialTheme.typography.caption
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

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

                Text(
                    text = campaign.date,
                    style = MaterialTheme.typography.body1
                )

                Text(
                    text = " • ",
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.primary
                    )
                )

                Text(
                    text = campaign.time,
                    style = MaterialTheme.typography.body1
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp),
                    imageVector = Icons.Rounded.Groups,
                    contentDescription = "Volunteer"
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    CampaignBar(
                        total = campaign.volunteerCount,
                        limit = campaign.limit
                    )

                    Text(
                        text = stringResource(id = R.string.campaign_count,
                            (campaign.limit - campaign.volunteerCount).toString(),
                            campaign.limit
                        ),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CampaignItemPreview() {
    DoFavourTheme {
        CampaignItem(
            campaign = HomeDummy.campaigns[0]
        )
    }
}

@Composable
fun CampaignBar(
    modifier: Modifier = Modifier,
    total: Int,
    limit: Int
) {
    val progress = total.toFloat() / limit.toFloat()

    LinearProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .height(10.dp)
            .clip(
                RoundedCornerShape(16.dp)
            ),
        progress = progress,
        color = MaterialTheme.colors.primary,
        backgroundColor = Color.Gray
    )
}

@Preview
@Composable
private fun CampaignBarPreview() {
    DoFavourTheme {
        CampaignBar(
            total = 450,
            limit = 1000
        )
    }
}