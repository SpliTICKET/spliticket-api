package com.terratrack.terra_track_api.service

import com.terratrack.terra_track_api.entity.User
import com.terratrack.terra_track_api.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun findByUserId(userId: UUID): User {
        return userRepository.findUserByUserId(userId)
    }

    fun findByUsername(username: String): User {
        return userRepository.findUserByUsername(username)
    }

    fun existsByUsername(username: String): Boolean {
        return userRepository.existsUserByUsername(username)
    }

    fun save(user: User): User {
        return userRepository.save(user)
    }
}