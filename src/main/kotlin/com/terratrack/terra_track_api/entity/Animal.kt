package com.terratrack.terra_track_api.entity

import jakarta.persistence.*
import java.util.*

@Entity
class Animal(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "animal_id")
    var animalId: UUID = UUID.randomUUID(),

    @Column(name = "name")
    var name: String,

    @Column(name = "species")
    var species: String,

    @Column(name = "owner_id")
    var ownerId: UUID,

    @Column(name = "enclosure_id")
    var enclosureId: UUID?
)