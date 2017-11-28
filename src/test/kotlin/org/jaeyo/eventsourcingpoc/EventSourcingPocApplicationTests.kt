package org.jaeyo.eventsourcingpoc

import org.jaeyo.eventsourcingpoc.common.random
import org.jaeyo.eventsourcingpoc.domain.elect.dto.ElectRequest
import org.jaeyo.eventsourcingpoc.domain.elect.model.*
import org.jaeyo.eventsourcingpoc.domain.elect.repository.*
import org.jaeyo.eventsourcingpoc.domain.elect.service.ElectionService
import org.jaeyo.eventsourcingpoc.domain.elect.vo.Gender
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import javax.persistence.EntityManager
import javax.transaction.TransactionManager

@RunWith(SpringRunner::class)
@SpringBootTest
class EventSourcingPocApplicationTests {
	@Autowired lateinit var em: EntityManager

	@Autowired lateinit var candidateRepo: CandidateRepository
	@Autowired lateinit var regionRepo: RegionRepository
	@Autowired lateinit var electionRepo: ElectionRepository
	@Autowired lateinit var electionRegionRepo: ElectionRegionRepository
	@Autowired lateinit var electionGenderRepo: ElectionGenderRepository
	@Autowired lateinit var electionService: ElectionService

	private fun initCandidates(count: Int): List<Candidate> {
		val candidates = (1 .. count).map { Candidate(name = "candidate_$it") }
		candidateRepo.save(candidates)

		val elections = candidates.map {
		Election(candidate = it, votes = 0)
		}
		electionRepo.save(elections)

		return candidates
	}

	private fun initRegions(count: Int, candidates: List<Candidate>): List<Region> {
		val regions = (1 .. count).map { Region(name = "region_$it") }
		regionRepo.save(regions)

		val electionsForRegion = mutableListOf<ElectionRegion>()
        regions.forEach {
			val region = it
			candidates.forEach {
				electionsForRegion.add(ElectionRegion(candidate = it, region = region, votes = 0))
			}
		}
		electionRegionRepo.save(electionsForRegion)

		return regions
	}

	private fun initGender(candidates: List<Candidate>) {
		val electionsForGender = mutableListOf<ElectionGender>()
		Gender.values().forEach {
			val gender = it
			candidates.forEach {
				electionsForGender.add(ElectionGender(candidate = it, gender = gender, votes = 0))
			}
		}
		electionGenderRepo.save(electionsForGender)
	}

	@Test
	fun testOverall() {
		val candidates = initCandidates(5)
		val regions = initRegions(10, candidates)
		initGender(candidates)

		val participateCount = 100L
		(1 .. participateCount).forEach {
			val req = ElectRequest(
				candidateId = candidates.random().id,
				regionId = regions.random().id,
				gender = Gender.values().random()
			)
			req.assertValid()
			electionService.elect(req)
		}

		val statistic = electionService.getStatistic()

		assertEquals(participateCount, statistic.elections.map { it.votes }.sum())
		assertEquals(participateCount, statistic.electionsPerRegion.map { it.votes }.sum())
		assertEquals(participateCount, statistic.electionsPerGender.map { it.votes }.sum())
	}
}
