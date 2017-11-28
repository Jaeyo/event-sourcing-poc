package org.jaeyo.eventsourcingpoc

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class EventSourcingPocApplication {
}

fun main(args: Array<String>) {
    SpringApplication.run(EventSourcingPocApplication::class.java, *args)
}
