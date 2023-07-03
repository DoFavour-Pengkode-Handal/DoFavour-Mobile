package com.example.dofavour.android.core_ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.rounded.History
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val icon: ImageVector
) {
    Home(
        icon = Icons.Filled.Home
    ),
    Post(
        icon = Icons.Default.Photo
    ),
    History(
        icon = Icons.Rounded.History
    ),
    Profile(
        icon = Icons.Filled.AccountCircle
    );

    companion object{
        fun fromRoute(route: String?): TopLevelDestination? =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                Post.name -> Post
                History.name -> History
                Profile.name -> Profile
                else -> null
            }

        fun getPosition(destination: TopLevelDestination): Int =
            when (destination) {
                Home -> 1
                Post -> 2
                History -> 3
                Profile -> 4
            }
    }
}