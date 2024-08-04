package com.spliticket.spliticket_api.controller

import com.spliticket.spliticket_api.dto.ArtistDto
import com.spliticket.spliticket_api.dto.EventDto
import com.spliticket.spliticket_api.entity.Artist
import com.spliticket.spliticket_api.entity.Event
import com.spliticket.spliticket_api.repository.ArtistRepository
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/artist")
class ArtistController(
    private val artistRepository: ArtistRepository,
) {

    @GetMapping
    fun getArtists(): ResponseEntity<List<ArtistDto>> {
        return ResponseEntity(
            artistRepository.findAll().sortedBy { artist: Artist -> artist.name }.map { artist: Artist ->
                ArtistDto(
                    artist.artistId,
                    artist.name,
                    artist.events.map { event: Event ->
                        EventDto(event.eventId, event.name, null, null, emptyList(), null)
                    })
            },
            HttpStatusCode.valueOf(200)
        )
    }

    @PostMapping
    @Secured("ROLE_MODERATOR")
    fun postArtist(@RequestBody artistDto: ArtistDto): ResponseEntity<ArtistDto?> {
        if (artistDto.name.isNullOrEmpty()) {
            return ResponseEntity(null, HttpStatusCode.valueOf(400))
        }

        val artist = Artist(null, artistDto.name, emptyList())
        artistRepository.save(artist)

        return ResponseEntity(ArtistDto(null, artist.name, emptyList()), HttpStatusCode.valueOf(200))
    }
}