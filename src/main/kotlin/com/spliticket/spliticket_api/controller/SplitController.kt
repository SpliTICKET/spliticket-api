package com.spliticket.spliticket_api.controller

import com.spliticket.spliticket_api.config.SecurityUtils.getCurrentUser
import com.spliticket.spliticket_api.dto.*
import com.spliticket.spliticket_api.entity.Artist
import com.spliticket.spliticket_api.entity.Split
import com.spliticket.spliticket_api.entity.SplitParticipant
import com.spliticket.spliticket_api.service.SplitService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/split")
class SplitController(private val splitService: SplitService) {

    @GetMapping
    fun getSplits(authentication: Authentication): ResponseEntity<List<SplitDto>> = ResponseEntity(
        splitService.findByOwner(getCurrentUser()!!).map { split: Split ->
            SplitDto(
                split.splitId, null, EventDto(
                    null,
                    split.event.name,
                    split.event.price,
                    VenueDto(split.event.venue),
                    split.event.artists.map { artist: Artist -> ArtistDto(artist) },
                    split.event.website,
                    split.event.imageUrl,
                    split.event.date
                ), emptyList(), split.locked.toString()
            )
        }, HttpStatus.OK
    )

    @PostMapping
    fun postSplit(authentication: Authentication, @RequestBody splitDto: SplitDto): ResponseEntity<SplitDto> {
        try {
            splitDto.owner = UserDto(getCurrentUser()!!)
            val split = splitService.createSplit(splitDto)

            return ResponseEntity(
                SplitDto(
                    split.splitId, UserDto(split.owner), EventDto(
                        null,
                        split.event.name,
                        split.event.price,
                        VenueDto(split.event.venue),
                        split.event.artists.map { artist: Artist -> ArtistDto(artist) },
                        split.event.website,
                        split.event.imageUrl,
                        split.event.date
                    ), split.splitParticipants.map { splitParticipant: SplitParticipant ->
                        SplitParticipantDto(
                            splitParticipant
                        )
                    }, split.locked.toString()
                ), HttpStatus.OK
            )
        } catch (exception: Exception) {
            return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping("/{splitId}")
    fun patchSplit(
        authentication: Authentication, @PathVariable splitId: UUID, @RequestBody splitDto: SplitDto
    ): ResponseEntity<SplitDto?> {
        var split: Split =
            splitService.findBySplitId(splitId) ?: return ResponseEntity(null, HttpStatusCode.valueOf(404))

        if (split.owner.userId != getCurrentUser()!!.userId) {
            return ResponseEntity(null, HttpStatus.FORBIDDEN)
        }

        split = splitService.patchSplit(split, splitDto)

        return ResponseEntity(
            SplitDto(
                split.splitId,
                UserDto(split.owner),
                EventDto(
                    null,
                    split.event.name,
                    split.event.price,
                    VenueDto(split.event.venue),
                    split.event.artists.map { artist: Artist -> ArtistDto(artist) },
                    split.event.website,
                    split.event.imageUrl,
                    split.event.date
                ),
                split.splitParticipants.map { splitParticipant: SplitParticipant ->
                    SplitParticipantDto(splitParticipant)
                },
                split.locked.toString()
            ), HttpStatus.OK
        )
    }

    @GetMapping("/{splitId}")
    fun getSplit(@PathVariable splitId: UUID): ResponseEntity<SplitDto?> {
        val split = splitService.findBySplitId(splitId)

        if (split == null) {
            return ResponseEntity(null, HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity(
                SplitDto(
                    split.splitId, UserDto(split.owner), EventDto(
                        split.event.eventId,
                        split.event.name,
                        split.event.price,
                        VenueDto(split.event.venue),
                        split.event.artists.map { artist: Artist -> ArtistDto(artist) },
                        split.event.website,
                        split.event.imageUrl,
                        split.event.date
                    ), split.splitParticipants.map { splitParticipant: SplitParticipant ->
                        SplitParticipantDto(
                            splitParticipant
                        )
                    }, split.locked.toString()
                ), HttpStatus.OK
            )
        }
    }

    @PostMapping("/{splitId}/splitParticipant")
    fun postSplitParticipant(
        @PathVariable splitId: UUID, @RequestBody splitParticipantDto: SplitParticipantDto
    ): ResponseEntity<SplitParticipantDto?> {
        return try {
            ResponseEntity(
                SplitParticipantDto(splitService.addSplitParticipant(splitId, splitParticipantDto)),
                HttpStatus.OK
            )
        } catch (exception: Exception) {
            ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }
    }
}