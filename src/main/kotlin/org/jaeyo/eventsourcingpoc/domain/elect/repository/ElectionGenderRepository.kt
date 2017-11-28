package org.jaeyo.eventsourcingpoc.domain.elect.repository

import org.jaeyo.eventsourcingpoc.domain.elect.model.ElectionGender
import org.jaeyo.eventsourcingpoc.domain.elect.vo.Gender
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ElectionGenderRepository : JpaRepository<ElectionGender, Long> {
    @Modifying
    @Query("update ElectionGender set votes = votes + 1 where candidate_id = :candidateId and gender = :gender")
    fun increaseVote(@Param("candidateId") candidateId: Long, @Param("gender") gender: Gender)
}
