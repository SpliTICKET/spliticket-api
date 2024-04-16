package com.spliticket.spliticket_api.dto

data class RegisterRequestDto(
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val email: String
)