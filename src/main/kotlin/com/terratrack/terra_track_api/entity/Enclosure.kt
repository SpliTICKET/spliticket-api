package com.terratrack.terra_track_api.entity

import jakarta.persistence.*
import java.util.*

@Entity
class Enclosure(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var enclosureId: UUID? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "length")
    var length: Int,

    @Column(name = "width")
    var width: Int,

    @Column(name = "height")
    var height: Int,

    @Column(name = "owner_id")
    var ownerId: UUID
)