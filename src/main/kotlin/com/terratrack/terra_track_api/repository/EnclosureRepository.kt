package com.terratrack.terra_track_api.repository

import com.terratrack.terra_track_api.entity.Enclosure
import jakarta.annotation.Nullable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface EnclosureRepository : JpaRepository<Enclosure, UUID> {
    fun findEnclosureByEnclosureIdAndOwnerId(enclosureId: UUID, ownerId: UUID): Enclosure?

    fun findEnclosuresByOwnerId(ownerId: UUID): Collection<Enclosure>
}