package com.terratrack.`terra-track-api`.repository

import com.terratrack.`terra-track-api`.entity.Animal
import jakarta.annotation.Nullable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AnimalRepository : JpaRepository<Animal, UUID> {
    @Nullable
    fun findAnimalByAnimalId(animalId: UUID): Animal?

    fun findAnimalByAnimalIdAndOwnerId(animalId: UUID, ownerId: UUID): Animal?

    fun findAnimalsByOwnerId(ownerId: UUID): Collection<Animal>
}