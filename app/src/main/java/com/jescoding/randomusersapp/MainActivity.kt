package com.jescoding.randomusersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.jescoding.randomusersapp.presentation.screens.users.UsersScreen
import com.jescoding.randomusersapp.presentation.UsersViewModel
import com.jescoding.randomusersapp.presentation.ui.theme.RandomUsersAppTheme

class MainActivity : ComponentActivity() {

    private val viewmodel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomUsersAppTheme {
                UsersScreen(viewmodel = viewmodel)
            }
        }
    }
}

