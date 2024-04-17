package com.spliticket.spliticket_api.service

import com.spliticket.spliticket_api.entity.User
import com.spliticket.spliticket_api.repository.SplitRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SplitService(
    private val splitRepository: SplitRepository
) {
    fun findBySplitId(splitId: UUID) = splitRepository.findSplitBySplitId(splitId)

    fun findByOwner(owner: User) = splitRepository.findSplitsByOwner(owner)
}