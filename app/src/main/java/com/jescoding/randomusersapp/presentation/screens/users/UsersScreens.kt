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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jescoding.randomusersapp.R
import com.jescoding.randomusersapp.presentation.UsersViewModel
import com.jescoding.randomusersapp.presentation.screens.users.components.InputField
import com.jescoding.randomusersapp.presentation.screens.users.components.UserCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewmodel: UsersViewModel
) {
    val isLoading by viewmodel.isLoading.collectAsState()
    val showBottomSheet by viewmodel.showBottomSheet.collectAsState()
    val users by viewmodel.users.collectAsState()
    val input by viewmodel.input.collectAsState()
    val inputError by viewmodel.inputError.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewmodel.showBottomSheet()
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
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                if (isLoading.not()) {
                    LazyColumn(modifier = modifier.fillMaxSize()) {
                        items(users.size) { index ->
                            val user = users[index]
                            UserCard(
                                name = user.name,
                                address = user.address,
                                imageUrl = user.imageUrl,
                                onClick = {
                                    navController.navigate("details")
                                    viewmodel.selectUser(user)
                                }
                            )
                        }
                    }
                }

                if (showBottomSheet) {
                    ModalBottomSheet(
                        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                        content = {
                            InputField(
                                input = input,
                                isError = inputError,
                                onValueChanged = {
                                    viewmodel.updateInput(it)
                                },
                                onGenerateUsers = {
                                    viewmodel.validateInput()
                                }
                            )
                        },
                        onDismissRequest = {
                            viewmodel.hideBottomSheet()
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
    UsersScreen(
        viewmodel = viewModel(),
        navController = rememberNavController()
    )
}