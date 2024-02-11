package com.terratrack.api.repository

import com.terratrack.api.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {
    fun findUserByUserId(userId: UUID): User

    fun existsUserByUsername(username: String): Boolean

    fun findUserByUsername(username: String): User
}