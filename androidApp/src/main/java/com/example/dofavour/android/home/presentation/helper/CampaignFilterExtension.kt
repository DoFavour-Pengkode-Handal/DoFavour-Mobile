package com.example.dofavour.android.home.presentation.helper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.rounded.School
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.dofavour.home.presentation.CampaignFilter
import com.example.dofavour.home.presentation.CampaignFilter.*

fun CampaignFilter.getIcon(): ImageVector {
    return when(this) {
        All -> Icons.Filled.GridView
        Nature -> Icons.Filled.Forest
        Health -> Icons.Default.MonitorHeart
        Education -> Icons.Rounded.School
    }
}