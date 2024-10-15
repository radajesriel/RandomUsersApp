package com.jescoding.randomusersapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jescoding.randomusersapp.data.User
import com.jescoding.randomusersapp.domain.RandomUsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class UsersUiState(
    val users: List<User> = emptyList(),
    val currentUser: User? = null,
    val input: String = "",
    val isLoading: Boolean = false,
    val showBottomSheet: Boolean = true,
    val inputError: Boolean = false,
    val error: String = ""
)

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: RandomUsersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UsersUiState())
    val uiState = _uiState.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(
            5000
        ), UsersUiState()
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
                input = input,
                error = ""
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
        val input = _uiState.value.input

        _uiState.update {
            it.copy(
                showBottomSheet = false,
                input = "",
                isLoading = true
            )
        }

        repository.getRandomUsersList(input).collect { users ->
            if (users.isEmpty()) {
                _uiState.update {
                    it.copy(
                        error = "An error occurred while fetching users",
                        isLoading = false
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        users = users,
                        isLoading = false
                    )
                }
            }
        }
    }

}