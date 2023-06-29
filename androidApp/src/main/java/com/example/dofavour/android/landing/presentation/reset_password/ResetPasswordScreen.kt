package com.example.dofavour.android.landing.presentation.reset_password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dofavour.android.R
import com.example.dofavour.android.core_ui.BasicTextField
import com.example.dofavour.android.core_ui.CommonHeader
import com.example.dofavour.android.core_ui.DefaultButton
import com.example.dofavour.android.core_ui.PasswordTextField
import com.example.dofavour.android.core_ui.theme.DoFavourTheme
import com.example.dofavour.android.core_ui.theme.LocalGradient
import com.example.dofavour.landing.presentation.reset_password.ResetPasswordEvent
import com.example.dofavour.landing.presentation.reset_password.ResetPasswordState

@Composable
fun ResetPasswordScreen(
    state: ResetPasswordState,
    onEvent: (ResetPasswordEvent) -> Unit,
    onBackClick: () -> Unit
) {
    val localGradient = LocalGradient.current

    Scaffold(
        topBar = {
            CommonHeader(
                modifier = Modifier
                    .padding(24.dp),
                title = stringResource(id = R.string.reset_password_title),
                onBackClick = onBackClick
            )
        },
        modifier = Modifier
            .background(localGradient.primary),
        backgroundColor = Color.Transparent
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BasicTextField(
                label = stringResource(id = R.string.email),
                text = state.email,
                onTextChange = {
                    onEvent(
                        ResetPasswordEvent.OnEmailChange(it)
                    )
                },
                error = state.emailError,
                color = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = MaterialTheme.colors.onPrimary,
                    cursorColor = MaterialTheme.colors.onBackground,
                    focusedLabelColor = MaterialTheme.colors.onBackground
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordTextField(
                title = stringResource(id = R.string.reset_password_textfield_desc),
                password = state.password,
                onPasswordChange = {
                    onEvent(
                        ResetPasswordEvent.OnPasswordChange(it)
                    )
                },
                passwordVisible = state.isPasswordVisible,
                showPassword = {
                    onEvent(
                        ResetPasswordEvent.ToggleShowPassword
                    )
                },
                error = state.passwordError,
                color = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = MaterialTheme.colors.onPrimary,
                    cursorColor = MaterialTheme.colors.onBackground,
                    focusedLabelColor = MaterialTheme.colors.onBackground
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            DefaultButton(
                modifier = Modifier,
                text = stringResource(id = R.string.reset_password_title),
                backgroundColor = MaterialTheme.colors.onBackground,
                textModifier = Modifier
                    .fillMaxWidth(),
                isEnabled = !state.isReset &&
                        state.email.isNotBlank() &&
                        state.emailError == null &&
                        state.password.isNotBlank() &&
                        state.passwordError == null
            ) {
                onEvent(
                    ResetPasswordEvent.Reset
                )
            }
        }
    }
}

@Preview
@Composable
private fun ResetPasswordScreenPreview() {
    DoFavourTheme {
        ResetPasswordScreen(
            state = ResetPasswordState(),
            onEvent = {  },
            onBackClick = {  }
        )
    }
}