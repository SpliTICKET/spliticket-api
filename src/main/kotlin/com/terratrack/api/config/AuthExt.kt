package com.terratrack.api.config

import com.terratrack.api.entity.User
import org.springframework.security.core.Authentication

fun Authentication.toUser(): User {
    return principal as User
}