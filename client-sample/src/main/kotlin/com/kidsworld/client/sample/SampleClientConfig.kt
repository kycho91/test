package com.kidsworld.client.sample

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Configuration
import java.security.Security

@Configuration
@EnableFeignClients(basePackages = ["com.kidsworld.client.sample"])
class SampleClientConfig: ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        Security.setProperty("networkaddress.cache.ttl", "0")
    }
}