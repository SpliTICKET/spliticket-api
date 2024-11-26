package com.spliticket.spliticket_api.controller

import com.spliticket.spliticket_api.dto.VenueDto
import com.spliticket.spliticket_api.entity.Venue
import com.spliticket.spliticket_api.service.VenueService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/venue")
class VenueController(val venueService: VenueService) {

    @GetMapping
    fun getVenues(): ResponseEntity<List<VenueDto>> =
        ResponseEntity(venueService.findAll().map { venue: Venue -> VenueDto(venue) }, HttpStatus.OK)

    @PostMapping
    @Secured("MODERATOR")
    fun postVenue(@RequestBody venueDto: VenueDto): ResponseEntity<VenueDto?> {
        try {
            if (venueDto.name === null || venueDto.name.trim().isEmpty()) throw Exception("Name is required")
            if (venueDto.address === null) throw Exception("Address is required")
            if (venueDto.website === null || venueDto.website.trim().isEmpty()) throw Exception("Website is required")

            val venue = venueService.createVenue(venueDto) ?: return ResponseEntity(null, HttpStatus.BAD_REQUEST)

            return ResponseEntity(VenueDto(venue), HttpStatus.CREATED)
        } catch (ex: Exception) {
            return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/{venueId}")
    fun getVenue(@PathVariable venueId: UUID): ResponseEntity<VenueDto?> {
        try {
            val venue = venueService.findByVenueId(venueId) ?: throw Exception("Venue not found")
            return ResponseEntity(VenueDto(venue), HttpStatus.OK)
        }catch (ex: Exception){
            return ResponseEntity(null, HttpStatus.NOT_FOUND)
        }
    }

    @PatchMapping("/{venueId}")
    @Secured("MODERATOR")
    fun patchVenue(@PathVariable venueId: UUID, @RequestBody venueDto: VenueDto): ResponseEntity<VenueDto?> {
        try {
            if (venueDto.name === null || venueDto.name.trim().isEmpty()) throw Exception("Name is required")
            if (venueDto.address === null) throw Exception("Name is required")
            if (venueDto.website === null || venueDto.website.trim().isEmpty()) throw Exception("Website is required")

            var venue = venueService.findByVenueId(venueId) ?: return ResponseEntity(null, HttpStatus.NOT_FOUND)
            venue = venueService.updateVenue(venue, venueDto)

            return ResponseEntity(VenueDto(venue), HttpStatus.OK)
        } catch (ex: Exception) {
            return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }
    }
}
