package com.spliticket.spliticket_api.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.spliticket.spliticket_api.entity.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtUtil {
    private val secret = "secret"

    fun extractUsername(token: String): String {
        return JWT.decode(token).subject
    }

    fun generateToken(user: User): String {
        return JWT.create()
            .withSubject(user.username)
            .withExpiresAt(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .withIssuedAt(Date(System.currentTimeMillis()))
            .sign(Algorithm.HMAC256(secret))
    }

    fun validateToken(token: String, user: User): Boolean {
        val username = extractUsername(token)
        return username == user.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return JWT.decode(token).expiresAt.before(Date())
    }
}
