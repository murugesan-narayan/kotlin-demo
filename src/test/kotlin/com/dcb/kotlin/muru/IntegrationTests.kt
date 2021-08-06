package com.dcb.kotlin.muru

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IntegrationTests (@Autowired val restTemplate : TestRestTemplate) {
    @BeforeAll
    fun setup(){
        println(">> Startup")
    }

    @Test
    fun `Testing Book Controller Add_Get_GetByTitle`() {

        //Add Book Request Test
        val book: Any = Book(isbn = "2276658", title = "KotlinGuide", id = null)
        val entity = restTemplate.postForEntity("/books/", book, Void::class.java)
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)

        //Get Books Request Test
        val respEntity = restTemplate.exchange("/books/", HttpMethod.GET, null,
            object : ParameterizedTypeReference<List<Book>>() {})

        val respBookList : List<Book> = respEntity.body as List<Book>
        assertThat(respBookList).hasSize(1)

        val respBook = respBookList[0]
        assertThat(respBook).isNotNull
        assertThat(respBook.id).isPositive
        assertThat(respBook.isbn).isEqualTo("2276658")
        assertThat(respBook.title).isEqualTo("KotlinGuide")

        assertThat(respEntity.statusCode).isEqualTo(HttpStatus.OK)

        //Get Book By Title Request
        val respEntityBookByTitle = restTemplate.exchange("/books/KotlinGuide", HttpMethod.GET, null,
            object : ParameterizedTypeReference<Book?>() {})

        val bookByTitle = respEntityBookByTitle.body as Book
        assertThat(bookByTitle).isNotNull
        assertThat(bookByTitle.id).isPositive
        assertThat(bookByTitle.isbn).isEqualTo("2276658")
        assertThat(bookByTitle.title).isEqualTo("KotlinGuide")

        assertThat(respEntityBookByTitle.statusCode).isEqualTo(HttpStatus.OK)
    }
}