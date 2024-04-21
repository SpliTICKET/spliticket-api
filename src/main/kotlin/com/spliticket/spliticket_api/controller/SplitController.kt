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
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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
                    null,
                    EventDto(
                        null,
                        split.event.name,
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

    @PostMapping
    fun postSplit(authentication: Authentication, @RequestBody splitDto: SplitDto): ResponseEntity<SplitDto> {
        try {
            val owner = authentication.toUser()

            splitDto.owner = UserDto(owner)
            val split = splitService.createSplit(splitDto)

            return ResponseEntity(
                SplitDto(
                    split.splitId,
                    UserDto(split.owner),
                    EventDto(
                        null,
                        split.event.name,
                        split.event.price.toString(),
                        VenueDto(split.event.venue),
                        split.event.artists.map { artist: Artist -> ArtistDto(artist) },
                        split.event.website
                    ),
                    split.splitParticipants.map { splitParticipant: SplitParticipant ->
                        SplitParticipantDto(
                            splitParticipant
                        )
                    },
                    split.locked.toString()
                ), HttpStatusCode.valueOf(200)
            )
        } catch (exception: Exception) {
            return ResponseEntity(null, HttpStatusCode.valueOf(400))
        }
    }

    @PatchMapping("/{splitId}")
    fun patchSplit(
        authentication: Authentication,
        @PathVariable splitId: String,
        @RequestBody splitDto: SplitDto
    ): ResponseEntity<SplitDto?> {
        val splitUUID: UUID
        try {
            splitUUID = UUID.fromString(splitId)
        } catch (exception: Exception) {
            return ResponseEntity(null, HttpStatusCode.valueOf(400))
        }

        var split: Split =
            splitService.findBySplitId(splitUUID) ?: return ResponseEntity(null, HttpStatusCode.valueOf(404))

        if (split.owner.userId != authentication.toUser().userId) {
            return ResponseEntity(null, HttpStatusCode.valueOf(403))
        }

        split = splitService.patchSplit(split, splitDto)

        return ResponseEntity(
            SplitDto(
                split.splitId,
                UserDto(split.owner),
                EventDto(
                    null,
                    split.event.name,
                    split.event.price.toString(),
                    VenueDto(split.event.venue),
                    split.event.artists.map { artist: Artist -> ArtistDto(artist) },
                    split.event.website
                ),
                split.splitParticipants.map { splitParticipant: SplitParticipant ->
                    SplitParticipantDto(
                        splitParticipant
                    )
                },
                split.locked.toString()
            ), HttpStatusCode.valueOf(200)
        )
    }

    @GetMapping("/{splitId}")
    fun getSplit(@PathVariable splitId: String): ResponseEntity<SplitDto?> {
        val splitUUID: UUID
        try {
            splitUUID = UUID.fromString(splitId)
        } catch (exception: Exception) {
            return ResponseEntity(null, HttpStatusCode.valueOf(400))
        }

        val split = splitService.findBySplitId(splitUUID)

        if (split == null) {
            return ResponseEntity(null, HttpStatusCode.valueOf(404));
        } else {
            return ResponseEntity(
                SplitDto(
                    split.splitId,
                    UserDto(split.owner),
                    EventDto(
                        split.event.eventId,
                        split.event.name,
                        split.event.price.toString(),
                        VenueDto(split.event.venue),
                        split.event.artists.map { artist: Artist -> ArtistDto(artist) },
                        split.event.website
                    ),
                    split.splitParticipants.map { splitParticipant: SplitParticipant ->
                        SplitParticipantDto(
                            splitParticipant
                        )
                    },
                    split.locked.toString()
                ), HttpStatusCode.valueOf(200)
            )
        }
    }

    @PostMapping("/{splitId}/splitParticipant")
    fun postSplitParticipant(
        @PathVariable splitId: String,
        @RequestBody splitParticipantDto: SplitParticipantDto
    ): ResponseEntity<SplitParticipantDto?> {
        val splitUUID: UUID
        try {
            splitUUID = UUID.fromString(splitId)
            return ResponseEntity(
                SplitParticipantDto(splitService.addSplitParticipant(splitUUID, splitParticipantDto)),
                HttpStatusCode.valueOf(200)
            )
        } catch (exception: Exception) {
            return ResponseEntity(null, HttpStatusCode.valueOf(400))
        }
    }
}