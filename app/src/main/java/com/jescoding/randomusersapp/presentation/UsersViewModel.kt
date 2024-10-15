package com.jescoding.randomusersapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jescoding.randomusersapp.data.User
import com.jescoding.randomusersapp.dispatchers.DispatcherProvider
import com.jescoding.randomusersapp.domain.RandomUsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class UsersUiState(
    val users: List<User> = emptyList(),
    val currentUser: User? = null,
    val input: String = "",
    val error: String = "",
    val shouldGenerateUsers: Boolean = false,
    val isLoading: Boolean = false,
    val showBottomSheet: Boolean = true,
    val inputError: Boolean = false,

    )

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: RandomUsersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UsersUiState())
    val uiState = _uiState.asStateFlow()

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

    fun validateInput() = viewModelScope.launch(dispatcherProvider.default) {
        val input = _uiState.value.input.toIntOrNull()
        val isOutsideRange = input != null && (input < 1 || input > 5000)

        if (input == null || isOutsideRange) {
            _uiState.update {
                it.copy(inputError = true)
            }
        } else {
            _uiState.update {
                it.copy(shouldGenerateUsers = true)
            }
        }
    }

    fun selectUser(user: User) {
        _uiState.update {
            it.copy(currentUser = user)
        }
    }

    fun generateUsers() = viewModelScope.launch(dispatcherProvider.io) {
        val input = _uiState.value.input

        _uiState.update {
            it.copy(
                showBottomSheet = false,
                input = "",
                isLoading = true,
                shouldGenerateUsers = false
            )
        }

        repository.getRandomUsersList(input)
            .catch {
                emit(emptyList())
            }
            .collect { users ->
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