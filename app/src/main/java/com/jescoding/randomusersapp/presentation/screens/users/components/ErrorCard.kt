package com.jescoding.randomusersapp.presentation.screens.users.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jescoding.randomusersapp.R

@Composable
fun ErrorCard(
    modifier: Modifier = Modifier,
    message: String = "An error occurred",
) {
    Box(
        modifier = modifier
            .size(240.dp)
            .padding(16.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.baseline_error_outline_24),
                contentDescription = "Error",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(all = 8.dp),
                text = message,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ErrorCardPreview() {
    ErrorCard(message = "An error occurred")
}