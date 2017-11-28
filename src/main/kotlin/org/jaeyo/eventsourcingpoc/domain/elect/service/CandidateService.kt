package org.jaeyo.eventsourcingpoc.domain.elect.service

import org.jaeyo.eventsourcingpoc.domain.elect.repository.CandidateRepository
import org.springframework.stereotype.Service

@Service
class CandidateService(
    val candidateRepo: CandidateRepository
) {
  fun isValidCandidateId(candidateId: Long) = candidateRepo.existsById(candidateId)
}
