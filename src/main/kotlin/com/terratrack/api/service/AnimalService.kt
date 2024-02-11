package com.terratrack.api.service

import com.terratrack.api.entity.Animal
import com.terratrack.api.repository.AnimalRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AnimalService(private val animalRepository: AnimalRepository) {

    fun findAnimalByAnimalId(animalId: UUID): Animal? {
        return animalRepository.findAnimalByAnimalId(animalId)
    }

    fun findAnimalByAnimalIdAndOwnerId(animalId: UUID, ownerId: UUID): Animal? {
        return animalRepository.findAnimalByAnimalIdAndOwnerId(animalId, ownerId)
    }

    fun findAnimalsByOwnerId(ownerId: UUID): Collection<Animal> {
        return animalRepository.findAnimalsByOwnerId(ownerId)
    }

    fun save(animal: Animal): Animal {
        return animalRepository.save(animal)
    }
}