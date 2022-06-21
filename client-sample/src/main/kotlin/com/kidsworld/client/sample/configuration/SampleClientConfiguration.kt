package com.kidsworld.client.sample.configuration

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

@EnableConfigurationProperties(SampleClientProperties::class)
class SampleClientConfiguration(private val sampleClientProperties: SampleClientProperties) {

    @Bean
    fun connectionManagerRequestInterceptor(): RequestInterceptor {
        return SampleRequestInterceptor(sampleClientProperties)
    }
}

class SampleRequestInterceptor(private val sampleClientProperties: SampleClientProperties): RequestInterceptor {

    override fun apply(template: RequestTemplate) {
        template.header("Content-Type", "application/json")
        template.header("Authorization", sampleClientProperties.accessKey)
    }

}