package com.terratrack.terra_track_api.config

import com.terratrack.terra_track_api.entity.User
import org.springframework.security.core.Authentication

fun Authentication.toUser(): User {
    return principal as User
}