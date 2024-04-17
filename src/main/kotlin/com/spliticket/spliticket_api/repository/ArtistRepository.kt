package com.spliticket.spliticket_api.repository

import com.spliticket.spliticket_api.entity.Artist
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ArtistRepository : JpaRepository<Artist, UUID> {
    fun findArtistByArtistId(artistId: UUID): Artist?
}