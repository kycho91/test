package com.kidsworld.client.sample.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "clients.sample")
class SampleClientProperties(
    val endpoint: String = "",
    val accessKey: String = ""
)