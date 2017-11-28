package org.jaeyo.eventsourcingpoc.domain.elect.command

import org.jaeyo.eventsourcingpoc.domain.elect.vo.Gender
import org.jaeyo.eventsourcingpoc.infra.event.Event

data class ElectedEvent(
    val candidateId: Long,
    val regionId: Long,
    val gender: Gender
) : Event {
    companion object {
        fun fromCommand(cmd: ElectCommand) =
            ElectedEvent(
                candidateId = cmd.candidateId,
                regionId = cmd.regionId,
                gender = cmd.gender
            )
    }
}
