package com.spliticket.spliticket_api.controller

import com.spliticket.spliticket_api.dto.LoginRequestDto
import com.spliticket.spliticket_api.dto.LoginResponseDto
import com.spliticket.spliticket_api.dto.RegisterRequestDto
import com.spliticket.spliticket_api.entity.User
import com.spliticket.spliticket_api.service.HashService
import com.spliticket.spliticket_api.service.TokenService
import com.spliticket.spliticket_api.service.UserService
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthController(
    private val hashService: HashService,
    private val tokenService: TokenService,
    private val userService: UserService,
) {
    @PostMapping("/login")
    fun postLogin(@RequestBody payload: LoginRequestDto): ResponseEntity<Any> {
        val user = userService.findByUsername(payload.username)

        if (user === null || !hashService.checkBcrypt(payload.password, user.password)) {
            return ResponseEntity("Wrong username or password", HttpStatusCode.valueOf(400))
        }

        return ResponseEntity(LoginResponseDto(tokenService.createToken(user)), HttpStatusCode.valueOf(200))
    }

    @PostMapping("/register")
    fun postRegister(@RequestBody payload: RegisterRequestDto): ResponseEntity<Any> {
        if (userService.existsByUsername(payload.username)) {
            return ResponseEntity("Username already exists", HttpStatusCode.valueOf(409))
        }

        val user = User(
            username = payload.username,
            password = hashService.hashBcrypt(payload.password),
            firstName = payload.firstName,
            lastName = payload.lastName,
            email = payload.email,
        )

        val savedUser = userService.save(user)

        return ResponseEntity(
            LoginResponseDto(tokenService.createToken(savedUser)),
            HttpStatusCode.valueOf(200)
        )
    }
}