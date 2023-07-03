package com.example.dofavour.android.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dofavour.android.R
import com.example.dofavour.android.core_ui.DefaultTextField
import com.example.dofavour.android.core_ui.theme.DoFavourTheme
import com.example.dofavour.android.home.presentation.helper.getIcon
import com.example.dofavour.home.presentation.CampaignFilter
import com.example.dofavour.home.presentation.HomeEvent
import com.example.dofavour.home.presentation.HomeState

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    onNotificationClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier
                ) {
                    Text(
                        text = stringResource(id = R.string.home_header) + " ",
                        style = MaterialTheme.typography.h5
                    )

                    Text(
                        text = "Someone",
                        style = MaterialTheme.typography.h5.copy(
                            color = Color.White
                        )
                    )
                }

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.LocationOn,
                        contentDescription = "Location",
                        tint = Color.White
                    )

                    Text(
                        text = "Jakarta, Indonesia"
                    )
                }
            }

            HomeNotificationButton(
                notificationCounts = 0,
                onNotificationClick = onNotificationClick
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        DefaultTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .background(
                    color = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(8.dp)
                ),
            text = state.searchText,
            hint = stringResource(id = R.string.search_hint),
            icon = Icons.Rounded.Search,
            onTextChange = {
                onEvent(
                    HomeEvent.OnSearchTextChange(it)
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = CampaignFilter.values(),
                key = { filter -> filter.name }
            ) { filter ->
                CampaignFilterItem(
                    filter = filter,
                    isSelected = state.filter == filter,
                    onClick = {
                        onEvent(
                            HomeEvent.SelectFilter(filter)
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeHeaderPreview() {
    DoFavourTheme {
        HomeHeader(
            state = HomeState(),
            onEvent = {  },
            onNotificationClick = {  }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CampaignFilterItem(
    modifier: Modifier = Modifier,
    filter: CampaignFilter,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val selectedModifier = if (isSelected) Modifier
        .border(
            width = 2.dp,
            color = MaterialTheme.colors.primary,
            shape = RoundedCornerShape(8.dp)
        ) else Modifier

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(70.dp)
                .then(selectedModifier),
            elevation = 5.dp,
            shape = RoundedCornerShape(8.dp),
            onClick = onClick
        ) {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier,
                    imageVector = filter.getIcon(),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = filter.name,
            color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground
        )
    }
}

@Preview
@Composable
private fun CampaignFilterItemSelectedPreview() {
    DoFavourTheme {
        CampaignFilterItem(
            filter = CampaignFilter.All,
            isSelected = true,
            onClick = {  }
        )
    }
}

@Preview
@Composable
private fun CampaignFilterItemUnselectedPreview() {
    DoFavourTheme {
        CampaignFilterItem(
            filter = CampaignFilter.All,
            isSelected = false,
            onClick = {  }
        )
    }
}

@Composable
private fun HomeNotificationButton(
    modifier: Modifier = Modifier,
    notificationCounts: Int,
    onNotificationClick: () -> Unit
) {
    IconButton(
        onClick = onNotificationClick,
        modifier = modifier
    ) {
        Box {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notification Icon",
                modifier = Modifier
                    .size(24.dp)
            )

            if (notificationCounts > 0) {
                val notificationText = if (notificationCounts > 9) "9+" else "$notificationCounts"

                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .clip(CircleShape)
                        .background(Color.Red)
                        .align(Alignment.TopEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = notificationText,
                        style = MaterialTheme.typography.caption.copy(
                            fontSize = 8.sp
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeNotificationButtonWithNotificationPreview() {
    DoFavourTheme {
        HomeNotificationButton(
            notificationCounts = 20,
            onNotificationClick = {  }
        )
    }
}

@Preview
@Composable
fun HomeNotificationButtonPreview() {
    DoFavourTheme {
        HomeNotificationButton(
            notificationCounts = 0,
            onNotificationClick = {  }
        )
    }
}
