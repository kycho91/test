package com.kidsworld.client.sample.client

import com.kidsworld.client.sample.configuration.SampleClientConfiguration
import com.kidsworld.client.sample.dto.SampleDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "sampleClient", url = "\${clients.sample.endpoint:sample.com}", configuration = [SampleClientConfiguration::class])
interface SampleClient {

    @GetMapping("dummy")
    fun getName(@RequestParam("name") name: String): SampleDto
}