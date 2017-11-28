package org.jaeyo.eventsourcingpoc.domain.elect.command

import org.jaeyo.eventsourcingpoc.domain.elect.dto.ElectRequest
import org.jaeyo.eventsourcingpoc.domain.elect.vo.Gender
import org.jaeyo.eventsourcingpoc.common.event.Command

data class ElectCommand(
    val candidateId: Long,
    val regionId: Long,
    val gender: Gender
) : Command {
    companion object {
        fun fromRequest(req: ElectRequest) =
            ElectCommand(
                candidateId = req.candidateId,
                regionId = req.regionId,
                gender = req.gender
            )
    }
}