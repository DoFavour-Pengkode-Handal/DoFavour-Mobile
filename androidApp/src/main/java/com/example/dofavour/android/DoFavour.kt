package com.example.dofavour.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dofavour.android.core_ui.navigation.Route
import com.example.dofavour.android.landing.presentation.boarding.BoardingScreen

@Composable
fun DoFavour(
    appState: AppState = rememberAppState(),
    shouldShowOnBoarding: Boolean = true
) {
    val scaffoldState = appState.scaffoldState
    val navController = appState.navController

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .padding(paddingValues),
            navController = navController,
            startDestination = Route.Boarding.name
        ) {
            composable(Route.Boarding.name) {
                BoardingScreen(
                    onLoginClick = {

                    },
                    onSignUpClick = {

                    }
                )
            }
        }
    }
}