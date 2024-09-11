package com.spliticket.spliticket_api.controller

import com.spliticket.spliticket_api.dto.EventDto
import com.spliticket.spliticket_api.dto.VenueDto
import com.spliticket.spliticket_api.entity.Event
import com.spliticket.spliticket_api.service.EventService
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

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
            EventDto(
                event.eventId,
                event.name,
                event.price.toString(),
                VenueDto(event.venue),
                emptyList(),
                event.website,
                event.imageUrl,
                event.date
            )
        )
    }
}