package com.electrodna.scheduler
import com.electrodna.scheduler.model.Schedule
import io.kotest.core.spec.style.FreeSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class IntegrationSpec(
    @Autowired restTemplate: RestTemplate
) : FreeSpec() {
    init {
        val uuid = "687d5858-7016-465e-8e5c-5a3f7785e9bf"
        restTemplate.getForEntity<Schedule>("http://localhost:8080/api/v1/schedule/$uuid")
    }

}