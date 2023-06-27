package com.example.dofavour.android.landing.presentation.verify_otp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dofavour.android.R
import com.example.dofavour.android.core_ui.CommonHeader
import com.example.dofavour.android.core_ui.DefaultButton
import com.example.dofavour.android.core_ui.theme.DoFavourTheme
import com.example.dofavour.android.landing.presentation.verify_otp.components.OtpTextField
import com.example.dofavour.landing.presentation.verify_otp.VerifyOtpEvent
import com.example.dofavour.landing.presentation.verify_otp.VerifyOtpState

@Composable
fun VerifyOtpScreen(
    email: String,
    state: VerifyOtpState,
    onEvent: (VerifyOtpEvent) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CommonHeader(
                modifier = Modifier
                    .padding(24.dp),
                title = stringResource(id = R.string.verify_otp_title),
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_prop_1),
                contentDescription = null,
                modifier = Modifier
                    .scale(2f)
                    .wrapContentSize(
                        align = Alignment.Center,
                        unbounded = true
                    )
                    .align(Alignment.BottomCenter)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter)
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.otp_icon),
                    contentDescription = "Otp Verification Icon",
                    modifier = Modifier
                        .size(200.dp)
                        .aspectRatio(1f)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.verify_otp_desc) + " ",
                        style = MaterialTheme.typography.caption
                    )

                    Text(
                        text = email,
                        style = MaterialTheme.typography.caption.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(64.dp))

                OtpTextField(
                    otpText = state.otp,
                    error = state.otpError,
                    onOtpTextChange = {
                        onEvent(
                            VerifyOtpEvent.OnOtpChange(it)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.resend_otp_desc) + " ",
                        style = MaterialTheme.typography.body2
                    )

                    Spacer(modifier = Modifier.width(2.dp))

                    Text(
                        text = if (state.otpCountDown == 0) stringResource(id = R.string.resend_otp_button)
                        else stringResource(id = R.string.resend_count, "${state.otpCountDown}"),
                        style = MaterialTheme.typography.body2.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.secondary
                        ),
                        modifier = Modifier
                            .then(
                                if (state.otpCountDown == 0) {
                                    Modifier
                                        .clickable {
                                            onEvent(VerifyOtpEvent.ReSendOtp)
                                        }
                                } else {
                                    Modifier
                                }
                            )
                    )
                }

                Spacer(modifier = Modifier.height(64.dp))

                DefaultButton(
                    modifier = Modifier,
                    text = stringResource(id = R.string.verify_otp_button),
                    backgroundColor = MaterialTheme.colors.onBackground,
                    textModifier = Modifier
                        .fillMaxWidth(),
                    isEnabled = !state.verifyOtpLoading &&
                            state.otp.length == 6 &&
                            state.otpError == null
                ) {
                    onEvent(
                        VerifyOtpEvent.OnVerifyOtp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun VerifyOtpScreenPreview() {
    DoFavourTheme {
        VerifyOtpScreen(
            email = "darrenthiores@gmail.com",
            state = VerifyOtpState(),
            onEvent = {  },
            onBackClick = {  }
        )
    }
}