package com.jescoding.randomusersapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jescoding.randomusersapp.data.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class UsersUiState(
    val users: List<User> = emptyList(),
    val currentUser: User? = null,
    val input: String = "",
    val isLoading: Boolean = false,
    val showBottomSheet: Boolean = true,
    val inputError: Boolean = false,
    val error: String = ""
)

class UsersViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UsersUiState())

    val currentUser = _uiState
        .map { it }
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(
                5000
            ), null
        )

    val inputError = _uiState
        .map { it.inputError }
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(
                5000
            ), false
        )

    val input = _uiState
        .map { it.input }
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(
                5000
            ), ""
        )

    val showBottomSheet = _uiState
        .map { it.showBottomSheet }
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(
                5000
            ), true
        )

    val users = _uiState
        .map { it.users }
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(
                5000
            ), emptyList()
        )

    val isLoading = _uiState
        .map { it.isLoading }
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(
                5000
            ), false
        )


    fun hideBottomSheet() {
        _uiState.update {
            it.copy(showBottomSheet = false)
        }
    }

    fun showBottomSheet() {
        _uiState.update {
            it.copy(showBottomSheet = true)
        }
    }

    fun updateInput(input: String) {
        _uiState.update {
            it.copy(
                inputError = false,
                input = input
            )
        }
    }

    fun validateInput() = viewModelScope.launch {
        val input = _uiState.value.input.toIntOrNull()
        val isOutsideRange = input != null && (input < 1 || input > 5000)

        if (input == null || isOutsideRange) {
            _uiState.update {
                it.copy(inputError = true)
            }
        } else {
            generateUsers()
        }
    }

    fun selectUser(user: User) {
        _uiState.update {
            it.copy(currentUser = user)
        }
    }

    private fun generateUsers() = viewModelScope.launch {
        _uiState.update {
            it.copy(
                showBottomSheet = false,
                input = "",
                isLoading = true
            )
        }

        delay(2000)

        val user = User(
            name = "Jesriel Carlo Rada",
            email = "jesriel.rada@example.com",
            address = "Cayetano St. Valenzuela City",
            username = "jescoding",
            gender = "Male",
            postalCode = "1440",
            birthday = "1995-07-01",
            phone = "123456789",
            mobile = "987654321",
            imageUrl = "https://randomuser.me/api/portraits/women/36.jpg"
        )

        val users = List(10) { user }

        _uiState.update {
            it.copy(
                users = users,
                isLoading = false
            )
        }

    }

}