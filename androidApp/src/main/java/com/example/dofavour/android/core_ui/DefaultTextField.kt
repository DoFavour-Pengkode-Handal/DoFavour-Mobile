package com.example.dofavour.android.core_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dofavour.android.core_ui.theme.DoFavourTheme

@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
    icon: ImageVector?,
    onTextChange: (String) -> Unit
) {
    androidx.compose.foundation.text.BasicTextField(
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        modifier = modifier,
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colors.onSurface
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp)
                    .heightIn(min = 40.dp, max = 120.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = hint,
                            style = LocalTextStyle.current.copy(
                                color = MaterialTheme.colors.onSurface.copy(
                                    alpha = 0.3f
                                )
                            )
                        )
                    }

                    innerTextField()
                }

                icon?.let {
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun DefaultTextFieldPreview() {
    DoFavourTheme {
        DefaultTextField(
            text = "",
            hint = "Search...",
            icon = Icons.Rounded.Search,
            onTextChange = {  }
        )
    }
}