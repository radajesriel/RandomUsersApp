package com.jescoding.randomusersapp.domain

import com.jescoding.randomusersapp.data.User
import kotlinx.coroutines.flow.Flow

interface RandomUsersRepository {

    suspend fun getRandomUsersList(number: String): Flow<List<User>>

}