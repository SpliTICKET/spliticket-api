package com.spliticket.spliticket_api.dto

import com.spliticket.spliticket_api.entity.Permission
import com.spliticket.spliticket_api.entity.User

data class UserDto(
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val permissions: List<PermissionDto>
) {
    constructor(user: User) : this(
        user.username,
        user.firstName,
        user.lastName,
        user.email,
        user.permissions.map { permission: Permission -> PermissionDto(permission) }
    )
}