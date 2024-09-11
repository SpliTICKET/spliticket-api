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
    val eventId: UUID,

    @Column(name = "name")
    val name: String,

    @Embedded
    val price: Price,

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "venue_id")
    val venue: Venue,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "event_has_artist",
        joinColumns = [JoinColumn(name = "event_id", referencedColumnName = "event_id")],
        inverseJoinColumns = [JoinColumn(name = "artist_id", referencedColumnName = "artist_id")]
    )
    val artists: List<Artist> = listOf(),

    @Column(name = "website")
    val website: String,

    @Column(name = "image-url")
    val imageUrl: URL,

    @Column(name = "date")
    val date: ZonedDateTime,
)