package com.spliticket.spliticket_api.controller

import com.spliticket.spliticket_api.config.JwtUtil
import com.spliticket.spliticket_api.dto.LoginRequestDto
import com.spliticket.spliticket_api.dto.LoginResponseDto
import com.spliticket.spliticket_api.dto.RegisterRequestDto
import com.spliticket.spliticket_api.entity.User
import com.spliticket.spliticket_api.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthController(
    @Autowired private val authenticationManager: AuthenticationManager,
    @Autowired private val jwtUtil: JwtUtil,
    @Autowired private val userService: UserService,
    @Autowired private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/login")
    fun createToken(@RequestBody payload: LoginRequestDto): ResponseEntity<LoginResponseDto?> {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(payload.username, payload.password)
        )

        val user: User = userService.findByUsername(payload.username)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok().body(LoginResponseDto(jwtUtil.generateToken(user)))
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterRequestDto): ResponseEntity<Any> {
        if (userService.existsByUsername(payload.username)) {
            return ResponseEntity.badRequest().body("Username already exists")
        }

        val user = User(
            username = payload.username,
            password = passwordEncoder.encode(payload.password),
            firstName = payload.firstName,
            lastName = payload.lastName,
            email = payload.email
        )

        val savedUser = userService.save(user)

        return ResponseEntity.ok().body(LoginResponseDto(jwtUtil.generateToken(savedUser)))
    }
}
