package com.terratrack.terra_track_api.service

import com.terratrack.terra_track_api.entity.Animal
import com.terratrack.terra_track_api.entity.Enclosure
import com.terratrack.terra_track_api.repository.AnimalRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AnimalService(private val animalRepository: AnimalRepository) {
    fun findAnimalByAnimalIdAndOwnerId(animalId: UUID, ownerId: UUID): Animal? =
        animalRepository.findAnimalByAnimalIdAndOwnerId(animalId, ownerId)

    fun findAnimalsByOwnerId(ownerId: UUID): Collection<Animal> = animalRepository.findAnimalsByOwnerId(ownerId)

    fun findAnimalByEnclosureId(enclosureId: UUID): Collection<Animal> =
        animalRepository.findAnimalsByEnclosureId(enclosureId)

    fun save(animal: Animal): Animal {
        return animalRepository.save(animal)
    }
}