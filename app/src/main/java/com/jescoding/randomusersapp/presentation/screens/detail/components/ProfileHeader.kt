package com.jescoding.randomusersapp.presentation.screens.detail.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jescoding.randomusersapp.R
import com.jescoding.randomusersapp.presentation.screens.components.CircularImage

@Composable
fun ProfileHeader(
    name: String,
    email: String,
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        CircularImage(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
                .border(2.dp, MaterialTheme.colorScheme.surface, CircleShape),
            imageUrl = imageUrl
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.surface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = email,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.surface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileHeaderPreview() {
    ProfileHeader(
        name = "John Doe",
        email = "john.quincy.adams@examplepetstore.com",
        imageUrl = "https://randomuser.me/api/portraits/men/64.jpg"
    )
}
