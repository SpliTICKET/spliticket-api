package com.spliticket.spliticket_api.repository

import com.spliticket.spliticket_api.entity.Venue
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface VenueRepository : JpaRepository<Venue, UUID> {
    fun findVenueByVenueId(venueId: UUID): Venue?
}