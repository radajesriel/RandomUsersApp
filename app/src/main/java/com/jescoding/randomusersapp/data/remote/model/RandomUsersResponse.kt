package com.jescoding.randomusersapp.data.remote.model

data class RandomUsersResponse(
    val results: List<Result>
)

data class Result(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val id: Id,
    val location: Location,
    val login: Login,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
)

data class Street(
    val name: String,
    val number: Int
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)

data class Name(
    val first: String,
    val last: String,
    val title: String
)

data class Login(
    val username: String
)

data class Location(
    val city: String,
    val country: String,
    val postcode: String,
    val state: String,
    val street: Street,
)

data class Id(
    val name: String,
    val value: Any
)

data class Dob(
    val age: Int,
    val date: String
)


