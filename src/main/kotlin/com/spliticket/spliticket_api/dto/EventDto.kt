package com.spliticket.spliticket_api.dto

import com.spliticket.spliticket_api.entity.Event
import java.net.URL
import java.time.ZonedDateTime
import java.util.*

data class EventDto(
    val eventId: UUID?,
    val name: String?,
    val price: String?,
    val venue: VenueDto?,
    val artists: List<ArtistDto>?,
    val website: String?,
    val imageUrl: URL?,
    val date: ZonedDateTime?
) {
    constructor(event: Event) : this(
        event.eventId,
        event.name,
        event.price.toString(),
        null,
        null,
        event.website,
        event.imageUrl,
        event.date
    )
}