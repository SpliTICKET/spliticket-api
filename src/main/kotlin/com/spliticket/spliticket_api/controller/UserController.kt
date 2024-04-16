package com.spliticket.spliticket_api.controller

import com.spliticket.spliticket_api.config.toUser
import com.spliticket.spliticket_api.dto.UserDto
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController {

    @GetMapping
    fun getUser(authentication: Authentication): ResponseEntity<UserDto> {
        val authUser = authentication.toUser()

        return ResponseEntity(
            UserDto(authUser),
            HttpStatusCode.valueOf(200)
        )
    }
}