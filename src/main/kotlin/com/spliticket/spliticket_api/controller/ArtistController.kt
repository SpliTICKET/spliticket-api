package com.spliticket.spliticket_api.controller

import com.spliticket.spliticket_api.dto.ArtistDto
import com.spliticket.spliticket_api.entity.Artist
import com.spliticket.spliticket_api.service.ArtistService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/artist")
class ArtistController(
    private val artistService: ArtistService
) {
    @GetMapping
    @Secured("MODERATOR")
    fun getArtists(): ResponseEntity<List<ArtistDto>> =
        ResponseEntity(artistService.findAll().sortedBy { artist: Artist -> artist.name }
            .map { artist: Artist -> ArtistDto(artist) },
            HttpStatus.OK
        )

    @PostMapping
    @Secured("MODERATOR")
    fun postArtist(@RequestBody artistDto: ArtistDto): ResponseEntity<ArtistDto?> {
        try {
            if (artistDto.name === null || artistDto.name.trim().isEmpty()) throw Exception("Name is required")

            val artist = artistService.createArtist(artistDto)

            return ResponseEntity(ArtistDto(artist), HttpStatus.CREATED)
        } catch (ex: Exception) {
            return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping("/{artistId}")
    @Secured("MODERATOR")
    fun patchArtist(@PathVariable artistId: UUID, @RequestBody artistDto: ArtistDto): ResponseEntity<ArtistDto?> {
        try {
            if (artistDto.name === null || artistDto.name.trim().isEmpty()) throw Exception("Name is required")

            var artist = artistService.findByArtistId(artistId) ?: return ResponseEntity(null, HttpStatus.NOT_FOUND)
            artist = artistService.updateArtist(artist, artistDto)

            return ResponseEntity(ArtistDto(artist), HttpStatus.CREATED)
        } catch (ex: Exception) {
            return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }
    }
}