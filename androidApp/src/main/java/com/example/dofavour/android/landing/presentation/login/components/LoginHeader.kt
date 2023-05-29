package com.example.dofavour.android.landing.presentation.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.dofavour.android.R
import com.example.dofavour.android.core_ui.theme.DoFavourTheme

@Composable
fun LoginHeader(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onCreateAccountClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackClick
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }
        
        Text(
            modifier = Modifier
                .clickable {
                    onCreateAccountClick()
                },
            text = stringResource(id = R.string.create_account),
            style = MaterialTheme.typography.h5.copy(
                fontSize = 18.sp
            )
        )
    }
}

@Preview
@Composable
private fun LoginHeaderPreview() {
    DoFavourTheme {
        LoginHeader(
            onBackClick = {  },
            onCreateAccountClick = {  }
        )
    }
}