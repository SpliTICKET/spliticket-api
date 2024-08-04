package com.spliticket.spliticket_api.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "artist")
data class Artist(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "artist_id")
    val artistId: UUID? = null,

    @Column(name = "name")
    val name: String,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "event_has_artist",
        joinColumns = [JoinColumn(name = "artist_id", referencedColumnName = "artist_id")],
        inverseJoinColumns = [JoinColumn(name = "event_id", referencedColumnName = "event_id")]
    )
    val events: Collection<Event>,
)