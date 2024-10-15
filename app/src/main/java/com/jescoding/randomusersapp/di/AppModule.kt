package com.jescoding.randomusersapp.di

import com.jescoding.randomusersapp.data.remote.RandomUsersService
import com.jescoding.randomusersapp.data.remote.repository.DefaultRandomUsersRepository
import com.jescoding.randomusersapp.domain.RandomUsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun providesRandomUsersService(retrofit: Retrofit): RandomUsersService {
        return retrofit.create(RandomUsersService::class.java)
    }

    @Provides
    @Singleton
    fun providesRandomUsersRepository(service: RandomUsersService): RandomUsersRepository {
        return DefaultRandomUsersRepository(service)
    }

}