package org.jaeyo.eventsourcingpoc.domain.elect.repository

import org.jaeyo.eventsourcingpoc.domain.elect.model.ElectionRegion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ElectionRegionRepository : JpaRepository<ElectionRegion, Long> {
    @Modifying
    @Query("update ElectionRegion set votes = votes + 1 where candidate_id = :candidateId and region_id = :regionId")
    fun increaseVote(@Param("candidateId") candidateId: Long, @Param("regionId") regionId: Long)
}
