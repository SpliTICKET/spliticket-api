package com.terratrack.api.controller

import com.terratrack.api.config.toUser
import com.terratrack.api.dto.EnclosureDto
import com.terratrack.api.service.EnclosureService
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/api/enclosure")
class EnclosureController(private val enclosureService: EnclosureService) {


    @GetMapping("/{enclosureId}")
    fun getEnclosure(authentication: Authentication, @PathVariable enclosureId: UUID): ResponseEntity<Any> {
//        TODO tryCatch enclosureId conversion
        val enclosure = enclosureService.findEnclosureByEnclosureId(enclosureId)

        if (enclosure.ownerId !== authentication.toUser().userId) {
            return ResponseEntity("Enclosure does not exist", HttpStatusCode.valueOf(404))
        }

        return ResponseEntity(
            EnclosureDto(enclosure.name, enclosure.length, enclosure.width, enclosure.height),
            HttpStatusCode.valueOf(200)
        )
    }
}