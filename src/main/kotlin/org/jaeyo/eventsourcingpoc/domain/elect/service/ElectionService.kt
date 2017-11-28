package org.jaeyo.eventsourcingpoc.domain.elect.service

import org.jaeyo.eventsourcingpoc.domain.elect.dto.ElectRequest
import org.jaeyo.eventsourcingpoc.domain.elect.dto.ElectionStatistic
import org.jaeyo.eventsourcingpoc.domain.elect.repository.ElectionGenderRepository
import org.jaeyo.eventsourcingpoc.domain.elect.repository.ElectionRegionRepository
import org.jaeyo.eventsourcingpoc.domain.elect.repository.ElectionRepository
import org.jaeyo.eventsourcingpoc.domain.elect.vo.Gender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ElectionService(
    val electionReactor: ElectionReactor,
    val electionRepo: ElectionRepository,
    val electionRegionRepo: ElectionRegionRepository,
    val electionGenderRepo: ElectionGenderRepository
) {
    fun elect(req: ElectRequest) = electionReactor.postElect(req)

    fun getStatistic() = ElectionStatistic(
        elections = electionRepo.findAll(),
        electionsPerRegion = electionRegionRepo.findAll(),
        electionsPerGender = electionGenderRepo.findAll()
    )

    @Transactional
    fun increaseVote(candidateId: Long, regionId: Long, gender: Gender) {
        electionRepo.increaseVote(candidateId)
        electionRegionRepo.increaseVote(candidateId, regionId)
        electionGenderRepo.increaseVote(candidateId, gender)
    }
}
