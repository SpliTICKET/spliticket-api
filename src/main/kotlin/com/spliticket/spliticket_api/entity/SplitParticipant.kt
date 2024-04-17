package com.spliticket.spliticket_api.entity

import jakarta.persistence.*
import java.util.*

@Entity
data class SplitParticipant(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "split_participant_id")
    val splitParticipantId: UUID,

    @Column(name = "first_name")
    val firstName: String,

    @Column(name = "last_name")
    val lastName: String?,

    @Column(name = "has_paid")
    val hasPaid: Boolean = false,
)