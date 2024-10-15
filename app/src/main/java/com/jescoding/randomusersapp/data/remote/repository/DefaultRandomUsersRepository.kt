package com.jescoding.randomusersapp.data.remote.repository

import com.jescoding.randomusersapp.data.User
import com.jescoding.randomusersapp.data.remote.RandomUsersService
import com.jescoding.randomusersapp.data.remote.model.RandomUsersResponse
import com.jescoding.randomusersapp.domain.RandomUsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultRandomUsersRepository @Inject constructor(
    private val service: RandomUsersService
) : RandomUsersRepository {

    override suspend fun getRandomUsersList(number: String): Flow<List<User>> = flow {
        val response = service.getRandomUsers(number)
        val users = if (response.isSuccessful) {
            mapToUsers(response.body())
        } else {
            emptyList()
        }

        emit(users)
    }

    private fun mapToUsers(response: RandomUsersResponse?): List<User> {
        return response?.results?.map {
            User(
                name = "${it.name.first} ${it.name.last}",
                email = it.email,
                address = "${it.location.street.number} ${it.location.street.name}, ${it.location.city}",
                imageUrl = it.picture.large,
                username = it.login.username,
                gender = it.gender,
                postalCode = it.location.postcode,
                birthday = it.dob.date,
                phone = it.phone,
                mobile = it.cell
            )
        } ?: emptyList()
    }

}


