package org.jaeyo.eventsourcingpoc.common

import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service

@Service
class SpringBeans(
    val ac: ApplicationContext
) {
    init {
        context = ac
    }

    companion object {
        var context: ApplicationContext? = null

        fun <B> of(clazz: Class<B>) = context!!.getBean(clazz)
    }
}
