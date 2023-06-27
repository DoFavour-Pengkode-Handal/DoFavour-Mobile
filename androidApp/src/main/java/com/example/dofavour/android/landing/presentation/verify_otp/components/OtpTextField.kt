package com.example.dofavour.android.landing.presentation.verify_otp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.core.utils.erros.ValidationError
import com.example.dofavour.android.core_ui.theme.DoFavourTheme

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 6,
    error: ValidationError?,
    onOtpTextChange: (String) -> Unit
) {
    var isTextFieldFocus by remember {
        mutableStateOf(false)
    }

    BasicTextField(
        modifier = modifier
            .onFocusChanged {
                isTextFieldFocus = it.isFocused
            },
        value = otpText,
        onValueChange = {
            if (it.length <= otpCount) {
                onOtpTextChange.invoke(it)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        text = otpText,
                        isFocus = isTextFieldFocus
                    )

                    if (index+1 != otpCount) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    )

    error?.let {
        Text(
            text = it.message ?: "Validation Error",
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
private fun CharView(
    index: Int,
    text: String,
    isFocus: Boolean
) {
    val isFocused = text.length == index && isFocus
    val char = when {
        index == text.length -> "0"
        index > text.length -> ""
        else -> text[index].toString()
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier
                .size(48.dp)
                .wrapContentHeight(Alignment.CenterVertically),
            text = char,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center
        )

        Divider(
            modifier = Modifier
                .width(50.dp)
                .background(
                    color = if (isFocused) {
                        MaterialTheme.colors.primary
                    } else {
                        Color.Gray
                    },
                    shape = RoundedCornerShape(32.dp)
                ),
            thickness = 4.dp
        )
    }
}

@Preview
@Composable
private fun OtpTextFieldPreview() {
    DoFavourTheme {
        OtpTextField(
            otpText = "",
            onOtpTextChange = {  },
            error = null
        )
    }
}