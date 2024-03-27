package com.terratrack.terra_track_api.repository

import com.terratrack.terra_track_api.entity.Animal
import jakarta.annotation.Nullable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AnimalRepository : JpaRepository<Animal, UUID> {
    fun findAnimalByAnimalIdAndOwnerId(animalId: UUID, ownerId: UUID): Animal?

    fun findAnimalsByOwnerId(ownerId: UUID): Collection<Animal>
}