package com.terratrack.terra_track_api.controller

import com.terratrack.terra_track_api.config.toUser
import com.terratrack.terra_track_api.dto.AnimalDto
import com.terratrack.terra_track_api.dto.EnclosureDto
import com.terratrack.terra_track_api.entity.Animal
import com.terratrack.terra_track_api.entity.Enclosure
import com.terratrack.terra_track_api.service.AnimalService
import com.terratrack.terra_track_api.service.EnclosureService
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api/enclosure")
class EnclosureController(private val enclosureService: EnclosureService, private val animalService: AnimalService) {

    @GetMapping
    fun getEnclosures(authentication: Authentication): ResponseEntity<List<EnclosureDto>> {
        val authUser = authentication.toUser()

        return ResponseEntity(
            enclosureService.findEnclosuresByOwnerId(authUser.userId)
                .map { enclosure: Enclosure ->
                    EnclosureDto(
                        enclosure,
                        null
                    )
                },
            HttpStatusCode.valueOf(200)
        )
    }

    @GetMapping("/{enclosureId}")
    fun getEnclosure(authentication: Authentication, @PathVariable enclosureId: String): ResponseEntity<Any> {
        val authUser = authentication.toUser()

        val enclosureUUID: UUID

        try {
            enclosureUUID = UUID.fromString(enclosureId)
        } catch (exception: IllegalArgumentException) {
            return ResponseEntity(null, HttpStatusCode.valueOf(400))
        }

        val enclosure = enclosureService.findEnclosureByEnclosureIdAndOwnerId(enclosureUUID, authUser.userId)

        if (enclosure === null) {
            return ResponseEntity(null, HttpStatusCode.valueOf(404))
        }

        return ResponseEntity(
            EnclosureDto(
                enclosure,
                animalService.findAnimalByEnclosureId(enclosure.enclosureId!!)
                    .map { animal: Animal -> AnimalDto(animal, null) }
            ),
            HttpStatusCode.valueOf(200)
        )
    }
}