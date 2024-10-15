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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jescoding.randomusersapp.presentation.UsersViewModel
import com.jescoding.randomusersapp.presentation.screens.detail.components.Details
import com.jescoding.randomusersapp.presentation.screens.detail.components.DetailsCard
import com.jescoding.randomusersapp.presentation.screens.detail.components.ProfileHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewmodel: UsersViewModel = UsersViewModel(),
    navController: NavController
) {

    val user = viewmodel.currentUser.collectAsState()

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
                    name = user.value?.name ?: "",
                    email = user.value?.email ?: "",
                    imageUrl = user.value?.imageUrl ?: ""
                )

                DetailsCard(
                    details = Details(
                        username = user.value?.username ?: "",
                        gender = user.value?.gender ?: "",
                        address = user.value?.address ?: "",
                        postalCode = user.value?.postalCode ?: "",
                        birthday = user.value?.birthday ?: "",
                        phone = user.value?.phone ?: "",
                        mobile = user.value?.mobile ?: ""
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
    DetailsScreen(navController = rememberNavController())
}