package org.jaeyo.eventsourcingpoc.admin

import com.vaadin.annotations.Theme
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.Button
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import org.jaeyo.eventsourcingpoc.common.random
import org.jaeyo.eventsourcingpoc.domain.elect.dto.ElectRequest
import org.jaeyo.eventsourcingpoc.domain.elect.model.*
import org.jaeyo.eventsourcingpoc.domain.elect.repository.*
import org.jaeyo.eventsourcingpoc.domain.elect.service.ElectionService
import org.jaeyo.eventsourcingpoc.domain.elect.vo.Gender

@SpringUI(path = "/admin")
@Theme("valo")
class HelloWorldUI(
    val candidateRepo: CandidateRepository,
    val electionRepo: ElectionRepository,
    val regionRepo: RegionRepository,
    val electionRegionRepo: ElectionRegionRepository,
    val electionGenderRepo: ElectionGenderRepository,
    val electionService: ElectionService
) : UI() {
    val candidateGridView = CandidateGridView()
    val regionGridView = RegionGridView()
    val electionGridView = ElectionGridView()

    override fun init(request: VaadinRequest?) {
        content = VerticalLayout(
            candidateGridView,
            regionGridView,
            electionGridView,
            buttons()
        )
    }

    private fun buttons() = VerticalLayout(
        initCandidateBtn(),
        initRegionBtn(),
        initGenderBtn(),
        goTestBtn()
    )

    private fun initCandidateBtn() =
        Button("init candidate").apply {
            addClickListener {
                val candidates = (1 .. 5).map { Candidate(name = "candidate_$it") }
                candidateRepo.saveAll(candidates)

                val elections = candidates.map { Election(candidate = it, votes = 0) }
                electionRepo.saveAll(elections)

                candidateGridView.refresh()
                electionGridView.refresh()
            }
        }

    private fun initRegionBtn() =
        Button("init region").apply {
            addClickListener {
                val regions = (1 .. 5).map { Region(name = "region_$it") }
                regionRepo.saveAll(regions)

                val candidates = candidateRepo.findAll()
                regions.forEach {
                    val region = it
                    candidates.forEach {
                        electionRegionRepo.save(ElectionRegion(candidate = it, region = region, votes = 0))
                    }
                }

                regionGridView.refresh()
                electionGridView.refresh()
            }
        }

    private fun initGenderBtn() =
        Button("init gender").apply {
            addClickListener {
                val candidates = candidateRepo.findAll()
                Gender.values().forEach {
                    val gender = it
                    candidates.forEach {
                        electionGenderRepo.save(ElectionGender(candidate = it, gender = gender, votes = 0))
                    }
                }

                electionGridView.refresh()
            }
        }

    private fun goTestBtn() =
        Button("go test").apply {
            addClickListener {
                val participateCount = 5000
                val candidates = candidateRepo.findAll()
                val regions = regionRepo.findAll()

                (1 .. participateCount).forEach {
                    val req = ElectRequest(
                        candidateId = candidates.random().id,
                        regionId = regions.random().id,
                        gender = Gender.values().random()
                    )
                    electionService.elect(req)
                }

                electionGridView.refresh()
            }
        }
}
