package com.terratrack.terra_track_api.dto

import com.terratrack.terra_track_api.entity.Animal
import com.terratrack.terra_track_api.entity.Enclosure
import java.util.*

class AnimalDto(
    val animalId: UUID?,
    val name: String,
    val species: String,
    val enclosureId: UUID?,
    val enclosure: Enclosure?
) {
    constructor(animal: Animal, enclosure: Enclosure?) : this(
        animal.animalId,
        animal.name,
        animal.species,
        animal.enclosureId,
        enclosure
    )
}
