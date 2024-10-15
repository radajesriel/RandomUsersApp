package com.jescoding.randomusersapp.presentation.screens.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DetailsCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        Column {
            DetailsItem(label = "Username:", value = "silverdog491")
            DetailsItem(label = "Gender:", value = "Male")
            DetailsItem(label = "Country:", value = "Denmark")
            DetailsItem(label = "Postal Code:", value = "G4U 7Z3")
            DetailsItem(label = "Birthday", value = "1970-01-31")
            DetailsItem(label = "Tel:", value = "367281928")
            DetailsItem(label = "Mobile:", value = "09564923118")
        }
    }
}


@Composable
@Preview
fun DetailsCardPreview() {
    DetailsCard()
}