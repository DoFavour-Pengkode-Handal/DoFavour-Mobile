package com.example.dofavour.android.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dofavour.android.core_ui.navigation.TopLevelDestination
import com.example.dofavour.android.core_ui.theme.DoFavourTheme
import com.example.dofavour.android.core_ui.theme.GreenBottomBar

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: TopLevelDestination?,
    onTabSelected: (TopLevelDestination) -> Unit,
) {
    Box(contentAlignment = Alignment.BottomCenter) {
        BottomBarClip(
            position = TopLevelDestination.getPosition(
                currentDestination ?: TopLevelDestination.Home
            )
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp + 28.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TopLevelDestination.values().forEach { destination ->
                if (currentDestination == destination) {
                    BottomNavigationItem(
                        modifier = Modifier
                            .height(56.dp),
                        icon = {
                            FloatingActionButton(
                                modifier = Modifier
                                    .offset(y = (-28).dp),
                                onClick = {
                                    onTabSelected(destination)
                                },
                                backgroundColor = GreenBottomBar
                            ) {
                                Icon(
                                    imageVector = destination.icon,
                                    contentDescription = destination.name + " icon",
                                    modifier = Modifier
                                        .size(24.dp),
                                    tint = Color.White
                                )
                            }
                        },
                        selected = true,
                        onClick = {
                            // onTabSelected(destination)
                        },
                        selectedContentColor = MaterialTheme.colors.secondary,
                        unselectedContentColor = Color.Black,
                        enabled = false
                    )
                } else {
                    BottomNavigationItem(
                        modifier = Modifier
                            .height(56.dp),
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = destination.name + " icon",
                                modifier = Modifier
                                    .size(24.dp),
                                tint = Color.Black
                            )
                        },
                        selected = false,
                        onClick = {
                            onTabSelected(destination)
                        },
                        selectedContentColor = MaterialTheme.colors.secondary,
                        unselectedContentColor = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun DefaultAppBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: TopLevelDestination?,
    onTabSelected: (TopLevelDestination) -> Unit,
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = GreenBottomBar
    ) {
        TopLevelDestination.values().forEach { destination ->
            BottomNavigationItem(
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (currentDestination == destination) {
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Icon(
                            imageVector = destination.icon,
                            contentDescription = destination.name + " icon",
                            modifier = Modifier
                                .size(24.dp),
                            tint = if(currentDestination == destination) Color.White else Color.Black
                        )

                        if (currentDestination == destination) {
                            Spacer(modifier = Modifier.weight(1f))

                            Box(
                                modifier = Modifier
                                    .width(50.dp)
                                    .height(2.dp)
                                    .background(
                                        Color.White,
                                        RoundedCornerShape(
                                            topStart = 8.dp,
                                            topEnd = 8.dp
                                        )
                                    )
                            )

                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                },
                selected = currentDestination == destination,
                onClick = {
                    onTabSelected(destination)
                },
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = Color.Black
            )
        }
    }
}

@Composable
private fun BottomBarClip(
    modifier: Modifier = Modifier,
    position: Int = 3
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        val maxWidth = constraints.maxWidth
        val maxHeight = constraints.maxHeight
        val iconCount = TopLevelDestination.values().size
        val iconWidth = (maxWidth/iconCount) * position - (maxWidth/iconCount/2)
        val iconSize = 32.dp + 2.dp

        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            val circle = Path().apply {
                addOval(
                    Rect(
                        center = Offset(
                            x = iconWidth.toFloat(),
                            y = 0f
                        ),
                        radius = iconSize.toPx()
                    )
                )
            }

            clipPath(
                path = circle,
                clipOp = ClipOp.Difference
            ) {
                drawRect(
                    color = GreenBottomBar
                )
            }
        }
    }
}

@Preview
@Composable
private fun BottomBarClipPreview() {
    DoFavourTheme {
        BottomBarClip()
    }
}

@Preview
@Composable
private fun AppBottomBarPreview() {
    DoFavourTheme {
        AppBottomBar(
            currentDestination = TopLevelDestination.Home,
            onTabSelected = {  }
        )
    }
}

@Preview
@Composable
private fun DefaultBottomBarPreview() {
    DoFavourTheme {
        DefaultAppBottomBar(
            currentDestination = TopLevelDestination.Home,
            onTabSelected = {  }
        )
    }
}