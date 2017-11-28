package org.jaeyo.eventsourcingpoc.common.config

import org.h2.server.web.WebServlet
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class H2Config {
    @Bean
    fun h2ServletRegistration() =
        ServletRegistrationBean(WebServlet()).apply {
            addUrlMappings("/console/*")
        }
}
