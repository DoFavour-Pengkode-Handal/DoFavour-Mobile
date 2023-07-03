package com.example.dofavour.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dofavour.android.components.DefaultAppBottomBar
import com.example.dofavour.android.core_ui.navigation.Route
import com.example.dofavour.android.core_ui.navigation.TopLevelDestination
import com.example.dofavour.android.home.presentation.AndroidHomeViewModel
import com.example.dofavour.android.home.presentation.HomeScreen
import com.example.dofavour.android.landing.presentation.boarding.BoardingScreen
import com.example.dofavour.android.landing.presentation.login.AndroidLoginViewModel
import com.example.dofavour.android.landing.presentation.login.LoginScreen
import com.example.dofavour.android.landing.presentation.register.AndroidRegisterViewModel
import com.example.dofavour.android.landing.presentation.register.RegisterScreen
import com.example.dofavour.android.landing.presentation.reset_password.AndroidResetPasswordViewModel
import com.example.dofavour.android.landing.presentation.reset_password.ResetPasswordScreen
import com.example.dofavour.android.landing.presentation.verify_otp.AndroidVerifyOtpViewModel
import com.example.dofavour.android.landing.presentation.verify_otp.VerifyOtpScreen
import com.example.dofavour.core.utils.UiEvent

@Composable
fun DoFavour(
    appState: AppState = rememberAppState(),
    shouldShowOnBoarding: Boolean = true
) {
    val scaffoldState = appState.scaffoldState
    val navController = appState.navController
    val currentDestination = TopLevelDestination.fromRoute(
        route = appState.currentDestination?.route
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState,
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                DefaultAppBottomBar(
                    currentDestination = currentDestination,
                    onTabSelected = {
                        navController.navigate(it.name)
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .padding(paddingValues),
            navController = navController,
            startDestination = if (shouldShowOnBoarding) Route.Boarding.name else TopLevelDestination.Home.name
        ) {
            composable(Route.Boarding.name) {
                BoardingScreen(
                    onLoginClick = {
                        navController.navigate(Route.Login.name)
                    },
                    onSignUpClick = {
                        navController.navigate(Route.Register.name)
                    }
                )
            }

            composable(Route.Login.name) {
                val viewModel: AndroidLoginViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                appState.showSnackBar("Login Succeed")
                                navController.navigate(TopLevelDestination.Home.name)
                            }
                            is UiEvent.ShowSnackBar -> appState.showSnackBar(event.message)
                            else -> Unit
                        }
                    }
                }

                LoginScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onBackClick = {
                        navController.navigateUp()
                    },
                    onSignUp = {
                        navController.navigate(Route.Register.name)
                    },
                    onForgotPassword = {
                        navController.navigate(Route.ResetPassword.name)
                    }
                )
            }

            composable(Route.Register.name) {
                val viewModel: AndroidRegisterViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                appState.showSnackBar("Register Succeed")
                                navController.navigate(Route.VerifyOtp.name + "/${state.email}")
                            }
                            is UiEvent.ShowSnackBar -> appState.showSnackBar(event.message)
                            else -> Unit
                        }
                    }
                }

                RegisterScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onBackClick = {
                        navController.navigateUp()
                    },
                    onLogin = {
                        navController.navigate(Route.Login.name)
                    }
                )
            }

            composable(
                route = Route.VerifyOtp.name + "/{email}",
                arguments = listOf(
                    navArgument("email") {
                        type = NavType.StringType
                    }
                )
            ) { navBackStackEntry ->
                val email = navBackStackEntry.arguments?.getString("email")

                val viewModel: AndroidVerifyOtpViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                appState.showSnackBar("Verify Otp Succeed")
                                navController.navigate(TopLevelDestination.Home.name)
                            }
                            is UiEvent.ShowSnackBar -> appState.showSnackBar(event.message)
                            else -> Unit
                        }
                    }
                }

                email?.let {
                    VerifyOtpScreen(
                        email = email,
                        state = state,
                        onEvent = viewModel::onEvent,
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
            }

            composable(Route.ResetPassword.name) {
                val viewModel: AndroidResetPasswordViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = true) {
                    viewModel.uiEvent.collect { event ->
                        when(event) {
                            is UiEvent.Success -> {
                                navController.navigate(Route.VerifyOtp.name + "/${state.email}")
                            }
                            is UiEvent.ShowSnackBar -> appState.showSnackBar(event.message)
                            else -> Unit
                        }
                    }
                }

                ResetPasswordScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(TopLevelDestination.Home.name) {
                val viewModel: AndroidHomeViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                HomeScreen(
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }
}