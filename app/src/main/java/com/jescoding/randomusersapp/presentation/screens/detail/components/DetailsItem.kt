package com.jescoding.randomusersapp.presentation.screens.detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DetailsItem(
    label: String = "Name:",
    value: String = "Jesriel Carlo Rada"
) {
    Row(modifier = Modifier.padding(12.dp)) {
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = value,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

    HorizontalDivider(modifier = Modifier.padding(horizontal = 12.dp))
}

@Composable
@Preview(showBackground = true)
fun DetailsItemPreview() {
    DetailsItem()
}