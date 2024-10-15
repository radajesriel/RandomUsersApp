package com.jescoding.randomusersapp.presentation.screens.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.jescoding.randomusersapp.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CircularImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    size: Dp = 100.dp
) {
    GlideImage(
        model = imageUrl,
        contentDescription = "Profile Picture",
        modifier = modifier
            .size(size)
            .clip(CircleShape),
        contentScale = ContentScale.Crop,
        loading = placeholder(R.drawable.baseline_person_24)
    )
}