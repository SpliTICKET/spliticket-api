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
    var venueId: UUID = UUID.randomUUID(),

    @Column(name = "name")
    val name: String,

    @Embedded
    val address: Address?,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, mappedBy = "venue")
    val events: List<Event>
)