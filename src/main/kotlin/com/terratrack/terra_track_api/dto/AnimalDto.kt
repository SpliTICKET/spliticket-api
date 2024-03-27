package com.terratrack.terra_track_api.dto

import java.util.*

class AnimalDto(
    val animalId: UUID?,
    val name: String,
    val species: String,
    val enclosureId: UUID?
)
