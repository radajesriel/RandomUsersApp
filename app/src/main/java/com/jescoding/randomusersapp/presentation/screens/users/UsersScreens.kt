package com.jescoding.randomusersapp.presentation.screens.users

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.jescoding.randomusersapp.R
import com.jescoding.randomusersapp.presentation.screens.users.components.InputField
import com.jescoding.randomusersapp.presentation.screens.users.components.UserCard
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(
    modifier: Modifier = Modifier
) {
    val isLoading = remember { mutableStateOf(true) }
    val showBottomSheet = remember { mutableStateOf(true) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showBottomSheet.value = true
                }
            ) {
                val imageVector = ImageVector.vectorResource(
                    id = R.drawable.baseline_create_24
                )
                Icon(
                    imageVector = imageVector,
                    contentDescription = "Add"
                )
            }
        },

        content = { innerPadding ->
            LaunchedEffect(Unit) {
                delay(5000)
                isLoading.value = false
            }
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (isLoading.value) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                if (isLoading.value.not()) {
                    LazyColumn(modifier = modifier.fillMaxSize()) {
                        items(10) {
                            UserCard()
                        }
                    }
                }

                if (showBottomSheet.value) {
                    ModalBottomSheet(
                        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                        content = {
                            InputField {
                                showBottomSheet.value = false
                            }
                        },
                        onDismissRequest = {
                            showBottomSheet.value = false
                        }
                    )
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun UsersScreenPreview() {
    UsersScreen()
}