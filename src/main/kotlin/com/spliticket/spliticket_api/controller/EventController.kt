package com.spliticket.spliticket_api.controller

import com.spliticket.spliticket_api.dto.EventDto
import com.spliticket.spliticket_api.entity.Event
import com.spliticket.spliticket_api.service.EventService
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/event")
class EventController(val eventService: EventService) {
    @GetMapping
    @Secured(value = ["TEST"])
    fun getEvents(): ResponseEntity<List<EventDto>> {
        return ResponseEntity(
            eventService.findAll().map { event: Event -> EventDto(event) },
            HttpStatusCode.valueOf(200)
        )
    }
}