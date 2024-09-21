package com.spliticket.spliticket_api.entity

import com.spliticket.spliticket_api.embeds.Address
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "venue")
data class Venue(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "venue_id")
    val venueId: UUID? = null,

    @Column(name = "name")
    var name: String,

    @Embedded
    var address: Address,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, mappedBy = "venue")
    val events: List<Event>,

    @Column(name = "website")
    var website: String,
)