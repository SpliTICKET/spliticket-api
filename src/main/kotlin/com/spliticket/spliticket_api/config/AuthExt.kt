package com.spliticket.spliticket_api.config

import com.spliticket.spliticket_api.entity.User
import org.springframework.security.core.Authentication

fun Authentication.toUser(): User {
    return principal as User
}