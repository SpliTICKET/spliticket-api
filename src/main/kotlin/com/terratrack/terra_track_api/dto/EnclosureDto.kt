package com.terratrack.terra_track_api.dto

import java.util.UUID

class EnclosureDto(
    val enclosureId: UUID?,
    val name: String,
    val length: Int,
    val width: Int,
    val height: Int,
    val residents: List<AnimalDto>?
)