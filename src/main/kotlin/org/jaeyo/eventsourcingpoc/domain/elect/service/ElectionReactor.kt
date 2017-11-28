package org.jaeyo.eventsourcingpoc.domain.elect.service

import com.google.common.eventbus.EventBus
import com.google.common.eventbus.Subscribe
import org.jaeyo.eventsourcingpoc.common.SpringBeans
import org.jaeyo.eventsourcingpoc.domain.elect.command.ElectCommand
import org.jaeyo.eventsourcingpoc.domain.elect.command.ElectedEvent
import org.jaeyo.eventsourcingpoc.domain.elect.dto.ElectRequest
import org.springframework.stereotype.Component

@Component
class ElectionReactor(
    val eventBus: EventBus
) {
    init {
        eventBus.register(this)
    }

    private fun electionService() = SpringBeans.of(ElectionService::class.java)

    fun postElect(req: ElectRequest) {
        val cmd = ElectCommand.fromRequest(req)
        eventBus.post(cmd)
    }

    @Subscribe
    fun onElectCommand(cmd: ElectCommand) {
        val event = ElectedEvent.fromCommand(cmd)
        eventBus.post(event)
    }

    @Subscribe
    fun onElectedEvent(event: ElectedEvent) {
        electionService().increaseVote(event.candidateId, event.regionId, event.gender)
    }
}
