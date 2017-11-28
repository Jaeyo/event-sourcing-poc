package org.jaeyo.eventsourcingpoc.common.config

import com.google.common.eventbus.EventBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventBusConfig {
    @Bean
    fun eventBus() = EventBus()
}
