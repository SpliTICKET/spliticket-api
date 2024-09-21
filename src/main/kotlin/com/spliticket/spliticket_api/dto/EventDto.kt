package com.spliticket.spliticket_api.dto

import com.spliticket.spliticket_api.embeds.Price
import com.spliticket.spliticket_api.entity.Artist
import com.spliticket.spliticket_api.entity.Event
import java.net.URL
import java.time.ZonedDateTime
import java.util.*

data class EventDto(
    val eventId: UUID?,
    val name: String?,
    val price: Price?,
    val venue: VenueDto?,
    val artists: List<ArtistDto>?,
    val website: URL?,
    val imageUrl: URL?,
    val date: ZonedDateTime?
) {
    constructor(event: Event) : this(
        event.eventId,
        event.name,
        event.price,
        VenueDto(event.venue),
        event.artists.map { artist: Artist -> ArtistDto(artist) },
        event.website,
        event.imageUrl,
        event.date
    )
}