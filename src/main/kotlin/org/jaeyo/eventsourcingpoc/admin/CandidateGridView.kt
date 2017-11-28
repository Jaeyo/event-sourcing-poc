package org.jaeyo.eventsourcingpoc.admin

import com.vaadin.ui.Grid
import com.vaadin.ui.VerticalLayout
import org.jaeyo.eventsourcingpoc.common.SpringBeans
import org.jaeyo.eventsourcingpoc.domain.elect.model.Candidate
import org.jaeyo.eventsourcingpoc.domain.elect.repository.CandidateRepository

class CandidateGridView : VerticalLayout() {
    private val grid = Grid<Candidate>().apply {
        addColumn(Candidate::id).setCaption("Id")
        addColumn(Candidate::name).setCaption("Name")
        addColumn(Candidate::createdAt).setCaption("CreatedAt")
    }

    init {
        addComponent(grid)
        refresh()
    }

    fun refresh() {
        grid.setItems(candidateRepo().findAll())
    }

    private fun candidateRepo() = SpringBeans.of(CandidateRepository::class.java)
}