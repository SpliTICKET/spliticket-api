package com.spliticket.spliticket_api.dto

import com.spliticket.spliticket_api.entity.SplitParticipant
import java.util.*

data class SplitParticipantDto(
    val splitParticipantId: UUID?,
    val name: String?,
    val hasPaid: Boolean?,
) {
    constructor(splitParticipant: SplitParticipant) : this(
        splitParticipant.splitParticipantId,
        splitParticipant.name,
        splitParticipant.hasPaid
    )
}