package com.spliticket.spliticket_api.entity

import jakarta.persistence.*
import java.util.*

@Entity
data class Split(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "split_id")
    val splitId: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    val owner: User,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id")
    val event: Event,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "split_id")
    val splitParticipants: MutableList<SplitParticipant>,

    @Column(name = "locked")
    @Temporal(TemporalType.DATE)
    val locked: Date?,
)