package com.kidsworld.web.infra.rds.datasource

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

@ConditionalOnProperty(prefix = "spring.datasource.using", name = ["devdb"])
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.kidsworld.web.domain.repository.devdb"],
    entityManagerFactoryRef = "devdbEntityManager",
    transactionManagerRef = "devdbTransactionManager")
class DevdbConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.devdb")
    fun devdbDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean
    fun devdbTransactionManager() = JpaTransactionManager(devdbEntityManager().`object`!!)

    @Bean
    fun devdbEntityManager(): LocalContainerEntityManagerFactoryBean =
        (LocalContainerEntityManagerFactoryBean()).apply {
            dataSource = devdbDataSource()
            setPackagesToScan("com.kidsworld.web.domain")
            jpaVendorAdapter = HibernateJpaVendorAdapter()
        }

    @Profile("local")
    @Bean
    fun devdbDataSourceInitializer(devdbDataSource: DataSource): DataSourceInitializer {
        val resourceDatabasePopulator = ResourceDatabasePopulator()
        resourceDatabasePopulator.addScript(ClassPathResource("h2/schema.sql"))
        resourceDatabasePopulator.addScript(ClassPathResource("h2/data.sql"))
        val dataSourceInitializer = DataSourceInitializer()
        dataSourceInitializer.setDataSource(devdbDataSource)
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator)
        return dataSourceInitializer
    }
}
