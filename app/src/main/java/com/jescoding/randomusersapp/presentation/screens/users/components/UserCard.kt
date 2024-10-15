package com.jescoding.randomusersapp.presentation.screens.users.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jescoding.randomusersapp.R
import com.jescoding.randomusersapp.presentation.screens.components.CircularImage
import com.jescoding.randomusersapp.presentation.ui.theme.Typography

@Composable
fun UserCard(
    name: String = "",
    address: String = "",
    picture: String = "",
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {

        val image = BitmapFactory.decodeResource(
            LocalContext.current.resources, R.drawable.profile
        ).asImageBitmap()

        Row {

            CircularImage(
                modifier = Modifier.padding(16.dp),
                image = image
            )

            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = name, style = Typography.titleMedium)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = address, style = Typography.bodyMedium)
                    Spacer(modifier = Modifier.size(4.dp))
                }
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun UserCardPreview() {
    UserCard(onClick =  {})
}