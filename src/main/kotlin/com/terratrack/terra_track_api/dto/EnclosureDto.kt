package com.terratrack.terra_track_api.dto

import com.terratrack.terra_track_api.entity.Enclosure
import java.util.UUID

class EnclosureDto(
    val enclosureId: UUID?,
    val name: String,
    val length: Int,
    val width: Int,
    val height: Int,
    val residents: Collection<AnimalDto>?
) {
    constructor(enclosure: Enclosure, residents: Collection<AnimalDto>?) : this(
        enclosure.enclosureId,
        enclosure.name,
        enclosure.length,
        enclosure.width,
        enclosure.height,
        residents
    )
}