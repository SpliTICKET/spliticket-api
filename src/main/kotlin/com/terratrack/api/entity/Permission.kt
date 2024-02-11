package com.terratrack.api.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "permission")
data class Permission(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "permission_id")
    var permissionId: UUID,

    @Column(name = "name")
    var name: String

)