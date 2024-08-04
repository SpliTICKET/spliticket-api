package com.spliticket.spliticket_api.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
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

) : GrantedAuthority {
    override fun getAuthority(): String {
        return name.uppercase()
    }
}