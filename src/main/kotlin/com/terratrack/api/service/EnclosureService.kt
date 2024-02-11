package com.terratrack.api.service

import com.terratrack.api.entity.Enclosure
import com.terratrack.api.repository.EnclosureRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class EnclosureService(
    private val enclosureRepository: EnclosureRepository
) {
    fun findEnclosureByEnclosureId(enclosureId: UUID): Enclosure {
        return enclosureRepository.findEnclosureByEnclosureId(enclosureId)
    }

    fun save(enclosure: Enclosure): Enclosure {
        return enclosureRepository.save(enclosure)
    }
}