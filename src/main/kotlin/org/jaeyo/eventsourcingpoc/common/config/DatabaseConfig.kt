package org.jaeyo.eventsourcingpoc.common.config

import org.h2.jdbcx.JdbcDataSource
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import java.util.*
import javax.persistence.EntityManagerFactory

//@Configuration
class DatabaseConfig {
    @Bean
    fun dataSource() = JdbcDataSource()

    @Bean
    fun entityManagerFactory(): EntityManagerFactory =
        LocalContainerEntityManagerFactoryBean().apply {
            dataSource = dataSource()
            setPackagesToScan("org.jaeyo.eventsourcingpoc")
            jpaVendorAdapter = HibernateJpaVendorAdapter()
            setJpaProperties(
                Properties().apply {
                    setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                }
            )
            afterPropertiesSet()
        }.`object`

    @Bean
    @ConditionalOnMissingBean(type = arrayOf("JpaTransactionManager"))
    fun transactionManager(emf: EntityManagerFactory) =
        JpaTransactionManager().apply {
            entityManagerFactory = emf
        }
}
