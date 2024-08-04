package com.spliticket.spliticket_api.config

import com.spliticket.spliticket_api.entity.User
import org.springframework.security.core.context.SecurityContextHolder

object SecurityUtils {
    fun getCurrentUser(): User? {
        val authentication = SecurityContextHolder.getContext().authentication
        return if (authentication.principal is User) {
            authentication.principal as User
        } else {
            null
        }
    }
}
