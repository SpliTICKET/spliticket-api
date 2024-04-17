package com.spliticket.spliticket_api.controller

import com.spliticket.spliticket_api.config.toUser
import com.spliticket.spliticket_api.dto.*
import com.spliticket.spliticket_api.entity.Artist
import com.spliticket.spliticket_api.entity.Split
import com.spliticket.spliticket_api.entity.SplitParticipant
import com.spliticket.spliticket_api.service.SplitService
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/split")
class SplitController(private val splitService: SplitService) {
    @GetMapping
    fun getSplits(authentication: Authentication): ResponseEntity<List<SplitDto>> = ResponseEntity(
        splitService.findByOwner(authentication.toUser())
            .map { split: Split ->
                SplitDto(
                    split.splitId,
                    EventDto(
                        null,
                        split.event.price.toString(),
                        VenueDto(split.event.venue),
                        split.event.artists.map { artist: Artist -> ArtistDto(artist) },
                        split.event.website
                    ),
                    null,
                    split.locked.toString()
                )
            },
        HttpStatusCode.valueOf(200)
    )

    @GetMapping("/{splitId}")
    fun getSplit(authentication: Authentication, @PathVariable splitId: String): ResponseEntity<SplitDto?> {
        val splitUUID: UUID;
        try {
            splitUUID = UUID.fromString(splitId)
        } catch (ex: IllegalArgumentException) {
            return ResponseEntity(null, HttpStatusCode.valueOf(400))
        }

        val split = splitService.findBySplitId(splitUUID)

        return if (split === null) ResponseEntity(null, HttpStatusCode.valueOf(404)) else ResponseEntity(
            SplitDto(
                split.splitId,
                EventDto(
                    null,
                    split.event.price.toString(),
                    VenueDto(split.event.venue),
                    split.event.artists.map { artist: Artist -> ArtistDto(artist) },
                    split.event.website
                ),
                split.splitParticipants.map { splitParticipant: SplitParticipant -> SplitParticipantDto(splitParticipant) },
                split.locked.toString()
            ), HttpStatusCode.valueOf(200)
        )
    }
}