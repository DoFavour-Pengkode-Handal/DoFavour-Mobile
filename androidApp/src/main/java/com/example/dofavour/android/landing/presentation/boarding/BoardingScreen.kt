package com.example.dofavour.android.landing.presentation.boarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dofavour.android.R
import com.example.dofavour.android.core_ui.DefaultButton
import com.example.dofavour.android.core_ui.theme.DoFavourTheme
import com.example.dofavour.android.core_ui.theme.LocalGradient

@Composable
fun BoardingScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val localGradient = LocalGradient.current

     Column(
         modifier = Modifier
             .fillMaxSize(),
         horizontalAlignment = Alignment.CenterHorizontally
     ) {
         Spacer(modifier = Modifier.weight(1f))

         Box(
             modifier = Modifier
                 .size(200.dp),
             contentAlignment = Alignment.Center
         ) {
             Text(text = "Logo")
         }

         Spacer(modifier = Modifier.weight(1f))

         Column(
             modifier = Modifier
                 .fillMaxWidth()
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

             Text(
                 text = stringResource(id = R.string.boarding_title),
                 style = MaterialTheme.typography.h1.copy(
                     color = MaterialTheme.colors.onPrimary
                 )
             )

             Spacer(modifier = Modifier.height(16.dp))
             
             Text(
                 modifier = Modifier
                     .fillMaxWidth(0.5f),
                 text = stringResource(id = R.string.boarding_subtitle),
                 style = MaterialTheme.typography.subtitle2.copy(
                     color = MaterialTheme.colors.onPrimary
                 )
             )

             Spacer(modifier = Modifier.height(64.dp))

             Row(
                 modifier = Modifier
                     .fillMaxWidth(),
                 horizontalArrangement = Arrangement.spacedBy(16.dp)
             ) {
                 DefaultButton(
                     modifier = Modifier
                         .weight(1f),
                     text = stringResource(id = R.string.login),
                     backgroundColor = MaterialTheme.colors.onBackground,
                     textModifier = Modifier
                         .fillMaxWidth()
                 ) {
                     onLoginClick()
                 }

                 DefaultButton(
                     modifier = Modifier
                         .weight(1f),
                     text = stringResource(id = R.string.sign_up),
                     backgroundColor = MaterialTheme.colors.background,
                     textModifier = Modifier
                         .fillMaxWidth(),
                     textColor = MaterialTheme.colors.onBackground
                 ) {
                     onSignUpClick()
                 }
             }

             Spacer(modifier = Modifier.height(64.dp))
         }
     }
}

@Preview
@Composable
private fun BoardingScreenPreview() {
    DoFavourTheme {
        BoardingScreen(
            onLoginClick = {  },
            onSignUpClick = {  }
        )
    }
}