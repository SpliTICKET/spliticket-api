package com.spliticket.spliticket_api.dto

import com.spliticket.spliticket_api.entity.Artist
import java.util.*

data class ArtistDto(
    val artistId: UUID?,
    val name: String?,
    val events: Collection<EventDto>?
) {
    constructor(artist: Artist) : this(artist.artistId, artist.name, null)
}