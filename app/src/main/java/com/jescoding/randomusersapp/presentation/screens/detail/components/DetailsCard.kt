package com.jescoding.randomusersapp.presentation.screens.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class Details(
    val username: String = "",
    val gender: String = "",
    val address: String = "",
    val postalCode: String = "",
    val birthday: String = "",
    val phone: String = "",
    val mobile: String = ""
)

@Composable
fun DetailsCard(
    details: Details,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        Column {
            DetailsItem(label = "Username:", value = details.username)
            DetailsItem(label = "Gender:", value = details.gender)
            DetailsItem(label = "Address:", value = details.address)
            DetailsItem(label = "Postal Code:", value = details.postalCode)
            DetailsItem(label = "Birthday", value = details.birthday)
            DetailsItem(label = "Tel:", value = details.phone)
            DetailsItem(label = "Mobile:", value = details.mobile)
        }
    }
}


@Composable
@Preview
fun DetailsCardPreview() {
    DetailsCard(details = Details())
}