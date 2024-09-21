package com.spliticket.spliticket_api.service

import com.spliticket.spliticket_api.dto.EventDto
import com.spliticket.spliticket_api.entity.Event
import com.spliticket.spliticket_api.repository.ArtistRepository
import com.spliticket.spliticket_api.repository.EventRepository
import com.spliticket.spliticket_api.repository.VenueRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val venueRepository: VenueRepository,
    private val artistRepository: ArtistRepository,
) {
    fun findAll(): List<Event> = eventRepository.findAll()

    fun findByEventId(eventId: UUID) = eventRepository.findEventByEventId(eventId)

    fun createEvent(eventDto: EventDto): Event {
        val venue = venueRepository.findVenueByVenueId(eventDto.venue!!.venueId!!) ?: throw Exception("venue not found")
        val artists = eventDto.artists!!.map { artistDto ->
            artistRepository.findArtistByArtistId(artistDto.artistId!!) ?: throw Exception("artist not found")
        }

        val event = Event(
            null,
            eventDto.name!!,
            eventDto.price!!,
            venue,
            artists,
            eventDto.website,
            eventDto.imageUrl,
            eventDto.date!!
        )

        return eventRepository.save(event)
    }

    fun updateEvent(event: Event, eventDto: EventDto): Event {
        val venue = venueRepository.findVenueByVenueId(eventDto.venue!!.venueId!!) ?: throw Exception("venue not found")
        val artists = eventDto.artists!!.map { artistDto ->
            artistRepository.findArtistByArtistId(artistDto.artistId!!) ?: throw Exception("artist not found")
        }

        event.name = eventDto.name!!
        event.price = eventDto.price!!
        event.venue = venue
        event.artists = artists
        event.website = eventDto.website
        event.imageUrl = eventDto.imageUrl
        event.date = eventDto.date!!

        return eventRepository.save(event)
    }

}