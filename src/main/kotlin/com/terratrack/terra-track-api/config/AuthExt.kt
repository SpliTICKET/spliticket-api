package com.terratrack.`terra-track-api`.config

import com.terratrack.`terra-track-api`.entity.User
import org.springframework.security.core.Authentication

fun Authentication.toUser(): User {
    return principal as User
}