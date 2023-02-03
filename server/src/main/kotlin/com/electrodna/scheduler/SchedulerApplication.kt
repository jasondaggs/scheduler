package com.electrodna.scheduler

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate




@SpringBootApplication
class SchedulerApplication {
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}

fun main(args: Array<String>) {
    runApplication<SchedulerApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}