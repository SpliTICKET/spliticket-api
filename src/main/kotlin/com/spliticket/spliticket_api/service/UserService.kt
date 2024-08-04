package com.spliticket.spliticket_api.service

import UserDetailsWrapper
import com.spliticket.spliticket_api.entity.User
import com.spliticket.spliticket_api.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
) : UserDetailsService {
    fun findByUserId(userId: UUID): User {
        return userRepository.findUserByUserId(userId)
    }

    fun findByUsername(username: String): User? = userRepository.findUserByUsername(username)

    fun existsByUsername(username: String): Boolean {
        return userRepository.existsUserByUsername(username)
    }

    fun save(user: User): User {
        return userRepository.save(user)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepository.findUserByUsername(username)
            ?: throw UsernameNotFoundException("User not found with username: $username")
        return UserDetailsWrapper(user)
    }
}