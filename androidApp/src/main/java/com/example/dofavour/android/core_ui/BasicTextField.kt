package com.example.dofavour.android.core_ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.core.utils.erros.ValidationError
import com.example.dofavour.android.core_ui.theme.DoFavourTheme

@Composable
fun BasicTextField(
    label: String,
    text: String,
    onTextChange: (String) -> Unit,
    error: ValidationError?,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    color: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            onTextChange(newText)
        },
        label = { Text(text = label) },
        isError = error != null,
        trailingIcon = {
            error?.let {
                IconButton(
                    onClick = {  }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Error,
                        contentDescription = it.message ?: "Validation Error",
                        tint = MaterialTheme.colors.error
                    )
                }
            }
        },
        keyboardOptions = keyboardOptions,
        singleLine = true,
        colors = color,
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
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

@Preview
@Composable
fun BasicTextFieldPreview() {
    DoFavourTheme {
        BasicTextField(
            label = "Email",
            text = "darrenthiores@gmail.com",
            onTextChange = {  },
            error = null
        )
    }
}