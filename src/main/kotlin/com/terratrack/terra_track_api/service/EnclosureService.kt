package com.terratrack.terra_track_api.service

import com.terratrack.terra_track_api.entity.Enclosure
import com.terratrack.terra_track_api.repository.EnclosureRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class EnclosureService(
    private val enclosureRepository: EnclosureRepository
) {
    fun findEnclosureByEnclosureIdAndOwnerId(enclosureId: UUID, ownerId: UUID): Enclosure? {
        return enclosureRepository.findEnclosureByEnclosureIdAndOwnerId(enclosureId, ownerId)
    }

    fun findEnclosuresByOwnerId(ownerId: UUID): Collection<Enclosure> {
        return enclosureRepository.findEnclosuresByOwnerId(ownerId)
    }

    fun save(enclosure: Enclosure): Enclosure {
        return enclosureRepository.save(enclosure)
    }
}