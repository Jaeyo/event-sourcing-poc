package org.jaeyo.eventsourcingpoc

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan
class EventSourcingPocApplication

fun main(args: Array<String>) {
    SpringApplication.run(EventSourcingPocApplication::class.java, *args)
}
