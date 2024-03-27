package com.terratrack.terra_track_api.repository

import com.terratrack.terra_track_api.entity.Permission
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PermissionRepository : JpaRepository<Permission, UUID> {
    fun findPermissionByName(name: String): Permission
}