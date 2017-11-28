package org.jaeyo.eventsourcingpoc.admin

import com.vaadin.ui.Grid
import com.vaadin.ui.VerticalLayout
import org.jaeyo.eventsourcingpoc.common.SpringBeans
import org.jaeyo.eventsourcingpoc.domain.elect.model.Election
import org.jaeyo.eventsourcingpoc.domain.elect.model.ElectionGender
import org.jaeyo.eventsourcingpoc.domain.elect.model.ElectionRegion
import org.jaeyo.eventsourcingpoc.domain.elect.repository.ElectionGenderRepository
import org.jaeyo.eventsourcingpoc.domain.elect.repository.ElectionRegionRepository
import org.jaeyo.eventsourcingpoc.domain.elect.repository.ElectionRepository

class ElectionGridView : VerticalLayout() {
    private val electionGrid = Grid<Election>().apply {
        addColumn(Election::candidate).setCaption("Candidate")
        addColumn(Election::votes).setCaption("Votes")
    }

    private val electionRegionGrid = Grid<ElectionRegion>().apply {
        addColumn(ElectionRegion::region).setCaption("Region")
        addColumn(ElectionRegion::candidate).setCaption("Candidate")
        addColumn(ElectionRegion::votes).setCaption("Votes")
    }

    private val electionGenderGrid = Grid<ElectionGender>().apply {
        addColumn(ElectionGender::gender).setCaption("Gender")
        addColumn(ElectionGender::candidate).setCaption("Candidate")
        addColumn(ElectionGender::votes).setCaption("Votes")
    }

    init {
        addComponent(electionGrid)
        addComponent(electionRegionGrid)
        addComponent(electionGenderGrid)
        refresh()
    }

    fun refresh() {
        electionGrid.setItems(electionRepo().findAll())
        electionRegionGrid.setItems(electionRegionRepo().findAll())
        electionGenderGrid.setItems(electionGenderRepo().findAll())
    }

    private fun electionRepo() = SpringBeans.of(ElectionRepository::class.java)
    private fun electionRegionRepo() = SpringBeans.of(ElectionRegionRepository::class.java)
    private fun electionGenderRepo() = SpringBeans.of(ElectionGenderRepository::class.java)
}