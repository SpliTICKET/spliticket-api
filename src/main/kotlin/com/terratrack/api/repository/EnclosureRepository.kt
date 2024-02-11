package com.terratrack.api.repository

import com.terratrack.api.entity.Enclosure
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface EnclosureRepository : JpaRepository<Enclosure, UUID> {
    fun findEnclosureByEnclosureId(enclosureId: UUID): Enclosure
}