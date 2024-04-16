package com.spliticket.spliticket_api.repository

import com.spliticket.spliticket_api.entity.Permission
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PermissionRepository : JpaRepository<Permission, UUID> {
    fun findPermissionByName(name: String): Permission
}