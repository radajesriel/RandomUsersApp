package com.jescoding.randomusersapp.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularImage(
    modifier: Modifier = Modifier,
    image: ImageBitmap,
    size: Dp = 100.dp
) {
    Image(
        bitmap = image,
        contentDescription = "Profile Picture",
        modifier = modifier
            .size(size)
            .clip(CircleShape)
    )
}