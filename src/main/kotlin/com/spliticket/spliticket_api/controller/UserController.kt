package com.spliticket.spliticket_api.controller

import com.spliticket.spliticket_api.entity.User
import com.spliticket.spliticket_api.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(val userService: UserService) {

    @GetMapping
    fun getUser(): ResponseEntity<Any> {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val authUser = authentication.principal as? User
        val username = authUser?.username ?: return ResponseEntity.notFound().build()
        val user = userService.findByUsername(username)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(user)
    }
}