package com.kidsworld.web.admin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.kidsworld.web"])
@ConfigurationPropertiesScan
class ApiAdminApplication

fun main(args: Array<String>) {
  runApplication<ApiAdminApplication>(*args)
}
