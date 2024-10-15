package com.jescoding.randomusersapp.presentation.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jescoding.randomusersapp.presentation.UsersViewModel
import com.jescoding.randomusersapp.presentation.screens.detail.components.Details
import com.jescoding.randomusersapp.presentation.screens.detail.components.DetailsCard
import com.jescoding.randomusersapp.presentation.screens.detail.components.ProfileHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewmodel: UsersViewModel,
    navController: NavController
) {

    val uiState by viewmodel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                title = { },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth()
                        .height(210.dp)
                )

                ProfileHeader(
                    modifier = Modifier.align(Alignment.TopCenter),
                    name = uiState.currentUser?.name ?: "",
                    email = uiState.currentUser?.email ?: "",
                    imageUrl = uiState.currentUser?.imageUrl ?: ""
                )

                DetailsCard(
                    details = Details(
                        username = uiState.currentUser?.username ?: "",
                        gender = uiState.currentUser?.gender ?: "",
                        address = uiState.currentUser?.address ?: "",
                        postalCode = uiState.currentUser?.postalCode ?: "",
                        birthday = uiState.currentUser?.birthday ?: "",
                        phone = uiState.currentUser?.phone ?: "",
                        mobile = uiState.currentUser?.mobile ?: ""
                    ),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 160.dp)
                )
            }
        }
    )

}


@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun DetailsScreenPreview() {
    DetailsScreen(
        viewmodel = viewModel(),
        navController = rememberNavController()
    )
}