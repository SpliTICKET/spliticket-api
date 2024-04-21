package com.spliticket.spliticket_api.dto

import com.spliticket.spliticket_api.entity.Split
import java.util.UUID

data class SplitDto(
    val splitId: UUID?,
    var owner: UserDto?,
    val event: EventDto?,
    val splitParticipants: List<SplitParticipantDto>?,
    val locked: String?
) {
    constructor(split: Split) : this(split.splitId, null, null, null, split.locked.toString())
}