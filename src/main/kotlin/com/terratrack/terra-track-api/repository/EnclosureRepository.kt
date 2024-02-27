package com.terratrack.`terra-track-api`.repository

import com.terratrack.`terra-track-api`.entity.Enclosure
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface EnclosureRepository : JpaRepository<Enclosure, UUID> {
    fun findEnclosureByEnclosureId(enclosureId: UUID): Enclosure
}