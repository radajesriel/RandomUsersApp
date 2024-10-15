package com.jescoding.randomusersapp.presentation.screens.users.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jescoding.randomusersapp.R
import com.jescoding.randomusersapp.presentation.ui.theme.Typography

@Composable
fun UserCard() {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
            .fillMaxWidth()
    ) {

        val image = BitmapFactory.decodeResource(
            LocalContext.current.resources, R.drawable.profile
        ).asImageBitmap()

        Row {

            CircularImage(image = image)

            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Jesriel Carlo Rada", style = Typography.titleMedium)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "5 Cayetano St, Marikina City", style = Typography.bodyMedium)
                    Spacer(modifier = Modifier.size(4.dp))
                }
            }
        }

    }
}

@Composable
fun CircularImage(image: ImageBitmap) {
    Image(
        bitmap = image,
        contentDescription = "Profile Picture",
        modifier = Modifier
            .padding(16.dp)
            .size(100.dp)
            .clip(CircleShape)
    )
}

@Composable
@Preview(showBackground = true)
fun UserCardPreview() {
    UserCard()
}