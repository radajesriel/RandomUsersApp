package com.jescoding.randomusersapp.presentation.screens.users.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun InputField(
    input: String,
    isError: Boolean = false,
    onValueChanged: (String) -> Unit,
    onGenerateUsers: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            Text(text = "Hi", style = MaterialTheme.typography.displayMedium)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                isError = isError,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = input,
                onValueChange = {
                    onValueChanged(it)
                },
                placeholder = {
                    Text(
                        text = "Please enter a number from 1 to 5000",
                        color = Color.DarkGray
                    )
                }
            )

            if (isError) {
                Text(
                    text = "Invalid input",
                    style = MaterialTheme.typography.labelMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    onGenerateUsers()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Generate Users")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun InputFieldPreview() {
    InputField(
        input = "10",
        onValueChanged = {},
        onGenerateUsers = {}
    )
}