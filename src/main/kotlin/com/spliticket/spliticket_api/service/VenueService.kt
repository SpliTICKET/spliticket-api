package com.spliticket.spliticket_api.service

import com.spliticket.spliticket_api.dto.VenueDto
import com.spliticket.spliticket_api.entity.Venue
import com.spliticket.spliticket_api.repository.VenueRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class VenueService(val venueRepository: VenueRepository) {
    fun findAll(): List<Venue> = venueRepository.findAll()

    fun findByVenueId(venueId: UUID) = venueRepository.findVenueByVenueId(venueId)

    fun createVenue(venueDto: VenueDto): Venue? {
        val venue = Venue(null, venueDto.name!!, venueDto.address!!, emptyList(), venueDto.website!!)

        return venueRepository.save(venue)
    }

    fun updateVenue(venue: Venue, venueDto: VenueDto): Venue {
        venue.name = venueDto.name!!
        venue.address = venueDto.address!!
        venue.website = venueDto.website!!
        return venueRepository.save(venue)
    }
}