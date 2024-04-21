package com.spliticket.spliticket_api.entity

import jakarta.persistence.*
import java.util.*

@Entity
data class SplitParticipant(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "split_participant_id")
    var splitParticipantId: UUID? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "has_paid")
    var hasPaid: Boolean = false,
)