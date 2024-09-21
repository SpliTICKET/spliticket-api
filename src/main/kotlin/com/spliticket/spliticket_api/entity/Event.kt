package com.spliticket.spliticket_api.entity

import com.spliticket.spliticket_api.embeds.Price
import jakarta.persistence.*
import java.net.URL
import java.time.ZonedDateTime
import java.util.*

@Entity
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "event_id")
    val eventId: UUID?,

    @Column(name = "name")
    var name: String,

    @Embedded
    var price: Price,

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "venue_id")
    var venue: Venue,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "event_has_artist",
        joinColumns = [JoinColumn(name = "event_id", referencedColumnName = "event_id")],
        inverseJoinColumns = [JoinColumn(name = "artist_id", referencedColumnName = "artist_id")]
    )
    var artists: List<Artist> = listOf(),

    @Column(name = "website")
    var website: URL?,

    @Column(name = "image-url")
    var imageUrl: URL?,

    @Column(name = "date")
    var date: ZonedDateTime,
)