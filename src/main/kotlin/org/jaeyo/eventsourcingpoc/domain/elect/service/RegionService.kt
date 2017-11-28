package org.jaeyo.eventsourcingpoc.domain.elect.service

import org.jaeyo.eventsourcingpoc.domain.elect.repository.RegionRepository
import org.springframework.stereotype.Service

@Service
class RegionService(
    val regionRepo: RegionRepository
) {
  fun isValidRegionId(regionId: Long) = regionRepo.exists(regionId)
}
