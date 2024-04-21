package com.spliticket.spliticket_api.service

import com.spliticket.spliticket_api.dto.SplitDto
import com.spliticket.spliticket_api.dto.SplitParticipantDto
import com.spliticket.spliticket_api.entity.Split
import com.spliticket.spliticket_api.entity.SplitParticipant
import com.spliticket.spliticket_api.entity.User
import com.spliticket.spliticket_api.repository.EventRepository
import com.spliticket.spliticket_api.repository.SplitRepository
import com.spliticket.spliticket_api.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SplitService(
    private val splitRepository: SplitRepository,
    private val userRepository: UserRepository,
    private val eventRepository: EventRepository,
) {
    fun findBySplitId(splitId: UUID) = splitRepository.findSplitBySplitId(splitId)

    fun findByOwner(owner: User) = splitRepository.findSplitsByOwner(owner)

    fun createSplit(splitDto: SplitDto): Split {
        val owner = userRepository.findUserByUsername(splitDto.owner!!.username) ?: throw Exception("Owner not Found")
        val event = eventRepository.findEventByEventId(splitDto.event!!.eventId!!) ?: throw Exception("event not Found")

        val split = Split(
            null,
            owner,
            event,
            (splitDto.splitParticipants ?: mutableListOf()).map { splitParticipantDto ->
                SplitParticipant(
                    null,
                    splitParticipantDto.name ?: "",
                    splitParticipantDto.hasPaid ?: false
                )
            }.toMutableList(),
            null
        )

        splitRepository.save(split)
        return split
    }

    fun patchSplit(split: Split, splitDto: SplitDto): Split {
        if (splitDto.splitParticipants != null) {
            val deletedSplitParticipants = split.splitParticipants
                .filter { splitParticipant -> splitDto.splitParticipants.find { splitParticipantDto -> splitParticipantDto.splitParticipantId == splitParticipant.splitParticipantId } == null }

            for (splitParticipant in deletedSplitParticipants) {
                split.splitParticipants.remove(splitParticipant)
            }

            for (splitParticipant in split.splitParticipants) {
                val updatedSplitParticipant =
                    splitDto.splitParticipants
                        .find { splitParticipantDto -> splitParticipantDto.splitParticipantId == splitParticipant.splitParticipantId }

                splitParticipant.name = updatedSplitParticipant!!.name ?: ""
                splitParticipant.hasPaid = updatedSplitParticipant.hasPaid ?: false
            }

            val newSplitParticipants =
                splitDto.splitParticipants.filter { splitParticipantDto -> splitParticipantDto.splitParticipantId == null }

            for (splitParticipant in newSplitParticipants) {
                split.splitParticipants.add(
                    SplitParticipant(
                        null,
                        splitParticipant.name ?: "",
                        splitParticipant.hasPaid ?: false
                    )
                )
            }
        }

        splitRepository.save(split)
        return split
    }

    fun addSplitParticipant(splitId: UUID, splitParticipantDto: SplitParticipantDto): SplitParticipant {
        val split = splitRepository.findSplitBySplitId(splitId) ?: throw Exception("Split not Found")

        val splitParticipant = SplitParticipant(null, splitParticipantDto.name ?: "", false)

        split.splitParticipants.add(splitParticipant)

        splitRepository.save(split)

        return splitParticipant;
    }
}