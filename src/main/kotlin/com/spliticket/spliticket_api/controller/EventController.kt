package com.spliticket.spliticket_api.controller

import com.spliticket.spliticket_api.dto.ArtistDto
import com.spliticket.spliticket_api.dto.EventDto
import com.spliticket.spliticket_api.dto.VenueDto
import com.spliticket.spliticket_api.entity.Artist
import com.spliticket.spliticket_api.entity.Event
import com.spliticket.spliticket_api.service.EventService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.math.E

@RestController
@RequestMapping("/api/event")
class EventController(val eventService: EventService) {
    @GetMapping
    @Secured("MODERATOR")
    fun getEvents(): ResponseEntity<List<EventDto>> {
        return ResponseEntity(
            eventService.findAll().map { event: Event -> EventDto(event) },
            HttpStatusCode.valueOf(200)
        )
    }

    @GetMapping("/{eventId}")
    @Secured("MODERATOR")
    fun getEvent(@PathVariable eventId: UUID): ResponseEntity<EventDto?> {
        val event = eventService.findByEventId(eventId) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok().body(
            EventDto(event)
        )
    }

    @PostMapping
    @Secured("MODERATOR")
    fun createEvent(@RequestBody eventDto: EventDto): ResponseEntity<EventDto?> {
        try {
            if (eventDto.price === null) throw Exception("price required")
            if (eventDto.venue === null || eventDto.venue.venueId === null) throw Exception("venue required")
            if (eventDto.artists === null || eventDto.artists.isEmpty()) throw Exception("artist required")
            for (artist in eventDto.artists) {
                if (artist.artistId === null) throw Exception("incorrect artist")
            }
            if (eventDto.date === null) throw Exception("date required")

            val event = eventService.createEvent(eventDto)

            return ResponseEntity(EventDto(event), HttpStatus.CREATED)
        } catch (ex: Exception) {
            return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping("/{eventId}")
    @Secured("MODERATOR")
    fun updateEvent(@PathVariable eventId: UUID, @RequestBody eventDto: EventDto): ResponseEntity<EventDto?> {
        var event = eventService.findByEventId(eventId) ?: return ResponseEntity(null, HttpStatus.NOT_FOUND)
        try {
            if (eventDto.price === null) throw Exception("price required")
            if (eventDto.venue === null || eventDto.venue.venueId === null) throw Exception("venue required")
            if (eventDto.artists === null || eventDto.artists.isEmpty()) throw Exception("artist required")
            for (artist in eventDto.artists) {
                if (artist.artistId === null) throw Exception("incorrect artist")
            }
            if (eventDto.date === null) throw Exception("date required")

            event = eventService.updateEvent(event, eventDto)

            return ResponseEntity(EventDto(event), HttpStatus.OK)
        }catch (ex: Exception){
            return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }
    }
}
