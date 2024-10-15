package com.jescoding.randomusersapp.data.remote

import com.jescoding.randomusersapp.data.remote.model.RandomUsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUsersService {

    @GET("api")
    suspend fun getRandomUsers(
        @Query("results") results: String
    ): Response<RandomUsersResponse>

}