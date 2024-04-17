package com.spliticket.spliticket_api.repository

import com.spliticket.spliticket_api.entity.Event
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface EventRepository : JpaRepository<Event, UUID> {
    fun findEventByEventId(eventId: UUID): Event?
}