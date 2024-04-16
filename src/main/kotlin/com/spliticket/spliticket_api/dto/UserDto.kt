package com.spliticket.spliticket_api.dto

import com.spliticket.spliticket_api.entity.User

data class UserDto(
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String
) {
    constructor(user: User) : this(user.username, user.firstName, user.lastName, user.email)
}