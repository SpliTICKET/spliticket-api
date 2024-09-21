package com.spliticket.spliticket_api.service

import com.spliticket.spliticket_api.dto.ArtistDto
import com.spliticket.spliticket_api.entity.Artist
import com.spliticket.spliticket_api.repository.ArtistRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ArtistService(
    private val artistRepository: ArtistRepository,
) {
    fun findAll(): MutableList<Artist> = artistRepository.findAll()

    fun findByArtistId(artistId: UUID) = artistRepository.findArtistByArtistId(artistId)

    fun createArtist(artistDto: ArtistDto): Artist {
        val artist = Artist(null, artistDto.name!!, emptyList())

        return artistRepository.save(artist)
    }

    fun updateArtist(artist: Artist, artistDto: ArtistDto): Artist {
        artist.name = artistDto.name!!
        return artistRepository.save(artist)
    }
}