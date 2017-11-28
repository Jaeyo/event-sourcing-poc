package org.jaeyo.eventsourcingpoc.domain.elect.repository

import org.jaeyo.eventsourcingpoc.domain.elect.model.Region
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RegionRepository : JpaRepository<Region, Long>
