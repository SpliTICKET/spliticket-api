package com.spliticket.spliticket_api.dto

import com.spliticket.spliticket_api.entity.SplitParticipant
import java.util.UUID

data class SplitParticipantDto(
    val splitParticipantId: UUID?,
    val firstName: String?,
    val lastName: String?,
    val hasPaid: Boolean?,
) {
    constructor(splitParticipant: SplitParticipant) : this(
        splitParticipant.splitParticipantId,
        splitParticipant.firstName,
        splitParticipant.lastName,
        splitParticipant.hasPaid
    )
}