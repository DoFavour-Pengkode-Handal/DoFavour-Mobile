package com.example.dofavour.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dofavour.android.core.domain.preferences.Preferences
import com.example.dofavour.android.core_ui.theme.DoFavourTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoFavourTheme {
                DoFavour(
                    shouldShowOnBoarding = preferences.loadShouldShowOnBoarding()
                )
            }
        }
    }
}
