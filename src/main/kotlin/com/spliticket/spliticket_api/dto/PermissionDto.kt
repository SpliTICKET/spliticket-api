package com.spliticket.spliticket_api.dto

import com.spliticket.spliticket_api.entity.Permission
import java.util.*

class PermissionDto(
    val permissionId: UUID?,
    val name: String,
) {
    constructor(permission: Permission) : this(null, permission.name)
}