package com.example.dofavour.android.landing.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dofavour.android.R
import com.example.dofavour.android.core_ui.BasicTextField
import com.example.dofavour.android.core_ui.DefaultButton
import com.example.dofavour.android.core_ui.PasswordTextField
import com.example.dofavour.android.core_ui.TitleLessHeader
import com.example.dofavour.android.core_ui.theme.DoFavourTheme
import com.example.dofavour.android.core_ui.theme.LocalGradient
import com.example.dofavour.landing.presentation.register.RegisterEvent
import com.example.dofavour.landing.presentation.register.RegisterState

@Composable
fun RegisterScreen(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit,
    onBackClick: () -> Unit,
    onLogin: () -> Unit
) {
    val localGradient = LocalGradient.current

    Scaffold(
        topBar = {
            TitleLessHeader(
                modifier = Modifier
                    .padding(24.dp),
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                text = stringResource(id = R.string.sign_up),
                style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        brush = localGradient.primary,
                        shape = RoundedCornerShape(
                            topStart = 32.dp,
                            topEnd = 32.dp
                        )
                    )
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(64.dp))

                BasicTextField(
                    label = stringResource(id = R.string.fullName),
                    text = state.fullName,
                    onTextChange = {
                        onEvent(
                            RegisterEvent.OnFulNameChange(it)
                        )
                    },
                    error = state.fullNameError,
                    color = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = MaterialTheme.colors.onPrimary,
                        cursorColor = MaterialTheme.colors.onBackground,
                        focusedLabelColor = MaterialTheme.colors.onBackground
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                BasicTextField(
                    label = stringResource(id = R.string.email),
                    text = state.email,
                    onTextChange = {
                        onEvent(
                            RegisterEvent.OnEmailChange(it)
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
                    password = state.password,
                    onPasswordChange = {
                        onEvent(
                            RegisterEvent.OnPasswordChange(it)
                        )
                    },
                    passwordVisible = state.isPasswordVisible,
                    showPassword = {
                        onEvent(
                            RegisterEvent.ToggleShowPassword
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
                    text = stringResource(id = R.string.sign_up),
                    backgroundColor = MaterialTheme.colors.onBackground,
                    textModifier = Modifier
                        .fillMaxWidth(),
                    isEnabled = state.registerButtonEnabled
                ) {
                    onEvent(
                        RegisterEvent.Register
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.have_acc),
                        style = MaterialTheme.typography.body2
                    )

                    Spacer(modifier = Modifier.width(2.dp))

                    Text(
                        text = stringResource(id = R.string.login),
                        style = MaterialTheme.typography.body2.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .clickable {
                                onLogin()
                            }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    DoFavourTheme {
        RegisterScreen(
            state = RegisterState(),
            onEvent = {  },
            onBackClick = {  },
            onLogin = {  }
        )
    }
}