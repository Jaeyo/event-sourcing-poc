package org.jaeyo.eventsourcingpoc.domain.elect.dto

import org.jaeyo.eventsourcingpoc.common.SpringBeans
import org.jaeyo.eventsourcingpoc.common.exception.InvalidRequestException
import org.jaeyo.eventsourcingpoc.domain.elect.service.CandidateService
import org.jaeyo.eventsourcingpoc.domain.elect.service.RegionService
import org.jaeyo.eventsourcingpoc.domain.elect.vo.Gender

data class ElectRequest (
    val candidateId: Long,
    val regionId: Long,
    val gender: Gender
) {
    private fun candidateService() = SpringBeans.of(CandidateService::class.java)
    private fun regionService() = SpringBeans.of(RegionService::class.java)

    fun assertValid() {
        val isValid = candidateService().isValidCandidateId(candidateId) and regionService().isValidRegionId(regionId)
        if (!isValid) {
            throw InvalidRequestException(toString())
        }
    }
}
