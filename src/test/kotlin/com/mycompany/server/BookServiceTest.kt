package com.mycompany.server

import io.mockk.every
import io.mockk.justRun
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(BookController::class)
internal class BookServiceTest(@Autowired val mockMvc: MockMvc){

    @MockBean
    private lateinit var bookRepository: BookRepository

    @MockBean
    private lateinit var bookService: BookService

    @Test
    internal fun `get all books`(){
        mockMvc.perform(
            get("/books")
        ).andExpect(status().isOk)
    }


}