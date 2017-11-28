package org.jaeyo.eventsourcingpoc.domain.elect.repository

import org.jaeyo.eventsourcingpoc.domain.elect.model.Election
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ElectionRepository: JpaRepository<Election, Long> {
    @Modifying
    @Query("update Election set votes = votes + 1 where candidate_id = :candidateId")
    fun increaseVote(@Param("candidateId") candidateId: Long)
}
