package com.spliticket.spliticket_api.dto

import com.spliticket.spliticket_api.entity.Event
import java.util.*

data class EventDto(
    val eventId: UUID?,
    val name: String?,
    val price: String?,
    val venue: VenueDto?,
    val artists: List<ArtistDto>?,
    val website: String?
) {
    constructor(event: Event) : this(event.eventId, event.name, event.price.toString(), null, null, event.website)
}