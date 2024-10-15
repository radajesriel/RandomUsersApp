package com.jescoding.randomusersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jescoding.randomusersapp.presentation.screens.users.UsersScreen
import com.jescoding.randomusersapp.presentation.UsersViewModel
import com.jescoding.randomusersapp.presentation.screens.detail.DetailsScreen
import com.jescoding.randomusersapp.presentation.ui.theme.RandomUsersAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewmodel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomUsersAppTheme {
                RootNavHost()
            }
        }
    }

    @Composable
    fun RootNavHost() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "users"
        ) {
            composable("users") {
                UsersScreen(
                    viewmodel = viewmodel,
                    navController = navController
                )
            }

            composable("details") {
                DetailsScreen(
                    viewmodel = viewmodel,
                    navController = navController
                )
            }
        }
    }

}

