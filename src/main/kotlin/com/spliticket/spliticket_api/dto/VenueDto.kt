package com.spliticket.spliticket_api.dto

import com.spliticket.spliticket_api.embeds.Address
import com.spliticket.spliticket_api.entity.Venue
import java.util.*

data class VenueDto(
    val venueId: UUID?,
    val name: String?,
    val address: Address?,
    val events: List<EventDto>?,
    val website: String?
) {
    constructor(venue: Venue) : this(venue.venueId, venue.name, venue.address, emptyList(), venue.website)
}