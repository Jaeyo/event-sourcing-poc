package org.jaeyo.eventsourcingpoc.admin

import com.vaadin.ui.Grid
import com.vaadin.ui.VerticalLayout
import org.jaeyo.eventsourcingpoc.common.SpringBeans
import org.jaeyo.eventsourcingpoc.domain.elect.model.Region
import org.jaeyo.eventsourcingpoc.domain.elect.repository.RegionRepository

class RegionGridView : VerticalLayout() {
    private val grid = Grid<Region>().apply {
        addColumn(Region::id).setCaption("Id")
        addColumn(Region::name).setCaption("Name")
        addColumn(Region::createdAt).setCaption("CreatedAt")
    }

    init {
        addComponent(grid)
        refresh()
    }

    fun refresh() {
        grid.setItems(regionRepo().findAll())
    }

    private fun regionRepo() = SpringBeans.of(RegionRepository::class.java)
}