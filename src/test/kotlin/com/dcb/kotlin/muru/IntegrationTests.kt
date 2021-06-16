package com.dcb.kotlin.muru

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IntegrationTests (@Autowired val restTemplate : TestRestTemplate){
    @BeforeAll
    fun setup(){
        println(">> Startup")
    }

    @Test
    fun `Testing Create Book`(){
        val entity = restTemplate.getForEntity<String>("/books/")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
    }
}