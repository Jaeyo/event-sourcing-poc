package org.jaeyo.eventsourcingpoc.domain.elect.controller

import org.jaeyo.eventsourcingpoc.domain.elect.dto.ElectRequest
import org.jaeyo.eventsourcingpoc.domain.elect.service.ElectionService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/elect/")
class ElectionController(
    val electionService: ElectionService
) {
    @PostMapping("/")
    fun elect(req: ElectRequest) {
        req.assertValid()
        electionService.elect(req)
    }
}
