package com.spliticket.spliticket_api.repository

import com.spliticket.spliticket_api.entity.Split
import com.spliticket.spliticket_api.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SplitRepository : JpaRepository<Split, UUID> {
    fun findSplitBySplitId(splitId: UUID): Split?

    fun findSplitsByOwner(owner: User): List<Split>
}
