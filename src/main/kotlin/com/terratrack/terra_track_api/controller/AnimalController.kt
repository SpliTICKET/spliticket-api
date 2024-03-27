package com.terratrack.terra_track_api.controller

import com.terratrack.terra_track_api.config.toUser
import com.terratrack.terra_track_api.dto.AnimalDto
import com.terratrack.terra_track_api.entity.Animal
import com.terratrack.terra_track_api.service.AnimalService
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/animal")
class AnimalController(private val animalService: AnimalService) {

    @GetMapping
    fun getAnimals(authentication: Authentication): ResponseEntity<List<AnimalDto>> {
        val authUser = authentication.toUser()

        return ResponseEntity(
            animalService.findAnimalsByOwnerId(authUser.userId)
                .map { animal: Animal -> AnimalDto(animal.animalId, animal.name, animal.species, animal.enclosureId) },
            HttpStatusCode.valueOf(200)
        )
    }

    @PostMapping
    fun postAnimal(authentication: Authentication, @RequestBody animalDto: AnimalDto): ResponseEntity<UUID> {
        val authUser = authentication.toUser()

        val animal = Animal(
            name = animalDto.name,
            species = animalDto.species,
            ownerId = authUser.userId,
            enclosureId = null
        )

        return ResponseEntity(animalService.save(animal).animalId, HttpStatusCode.valueOf(201))
    }

    @GetMapping("/{animalId}")
    fun getAnimal(authentication: Authentication, @PathVariable animalId: String): ResponseEntity<AnimalDto?> {
        val authUser = authentication.toUser()

        val animalUUID: UUID

        try {
            animalUUID = UUID.fromString(animalId)
        }catch (exception: IllegalArgumentException){
            return ResponseEntity(null, HttpStatusCode.valueOf(400))
        }

        val animal = animalService.findAnimalByAnimalIdAndOwnerId(animalUUID, authUser.userId)

        if (animal === null){
            return ResponseEntity(null, HttpStatusCode.valueOf(404))
        }

        return ResponseEntity(
            AnimalDto(animal.animalId, animal.name, animal.species, animal.enclosureId),
            HttpStatusCode.valueOf(200)
        )
    }
}
