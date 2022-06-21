package com.kidsworld.core.config.datasource

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.io.ClassPathResource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.init.DataSourceInitializer
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@ConditionalOnProperty(prefix = "spring.datasource.using", name = ["dev2db"])
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.kidsworld.web.domain.repository.dev2db"],
    entityManagerFactoryRef = "dev2dbEntityManager",
    transactionManagerRef = "dev2dbTransactionManager")
class Dev2dbConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.dev2db")
    fun dev2dbDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean
    fun dev2dbTransactionManager() = JpaTransactionManager(dev2dbEntityManager().`object`!!)

    @Bean
    fun dev2dbEntityManager(): LocalContainerEntityManagerFactoryBean =
        (LocalContainerEntityManagerFactoryBean()).apply {
            dataSource = dev2dbDataSource()
            setPackagesToScan("com.kidsworld.web.domain")
            jpaVendorAdapter = HibernateJpaVendorAdapter()
        }

    @Profile("local")
    @Bean
    fun dev2dbDataSourceInitializer(dev2dbDataSource: DataSource): DataSourceInitializer {
        val resourceDatabasePopulator = ResourceDatabasePopulator()
        resourceDatabasePopulator.addScript(ClassPathResource("h2/schema.sql"))
        resourceDatabasePopulator.addScript(ClassPathResource("h2/data.sql"))
        val dataSourceInitializer = DataSourceInitializer()
        dataSourceInitializer.setDataSource(dev2dbDataSource)
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator)
        return dataSourceInitializer
    }
}
