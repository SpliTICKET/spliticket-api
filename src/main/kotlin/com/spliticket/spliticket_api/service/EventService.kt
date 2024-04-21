package com.spliticket.spliticket_api.service

import com.spliticket.spliticket_api.entity.Event
import com.spliticket.spliticket_api.repository.EventRepository
import org.springframework.stereotype.Service

@Service
class EventService(
    private val eventRepository: EventRepository,
) {
    fun findAll() : List<Event> = eventRepository.findAll()
}