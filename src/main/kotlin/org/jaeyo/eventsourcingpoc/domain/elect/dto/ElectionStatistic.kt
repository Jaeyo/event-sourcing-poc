package org.jaeyo.eventsourcingpoc.domain.elect.dto

import org.jaeyo.eventsourcingpoc.domain.elect.model.Election
import org.jaeyo.eventsourcingpoc.domain.elect.model.ElectionGender
import org.jaeyo.eventsourcingpoc.domain.elect.model.ElectionRegion

data class ElectionStatistic(
    val elections: List<Election>,
    val electionsPerRegion: List<ElectionRegion>,
    val electionsPerGender: List<ElectionGender>
)
