package com.mycompany.server

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(BookController::class)
internal class BookControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var bookService: BookService

    private val mockBooks = listOf(
        Book(0, "Test title 0", "Jon Doe", false),
        Book(1, "Test title 1", "Jon Doe", false),
        Book(2, "Test title 2", "Lara Doe", false)
    )
    private val mockBookToCreate = Book(3, "Test title 3", "Andrew Doe", false)

    private val mapper = jacksonObjectMapper()

    @BeforeEach
    internal fun setupMocking() {
        every { bookService.getAll() } returns mockBooks
        every { bookService.createBook(any()) } returns mockBookToCreate

    }

    @Test
    internal fun `get all books`() {
        mockMvc.perform(
            get("/books")
        )
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
    }

    @Test
    internal fun `create a book - returns success`() {
        mockMvc.perform(
            post("/books")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockBookToCreate))
        ).andExpect(status().isCreated)
        verify(exactly = 1) { bookService.createBook(any()) }
    }

    @Test
    internal fun `delete a book - returns no content`() {
        val idToDelete = "2"
        every { bookService.deleteBookById(idToDelete.toLong()) } returns true
        mockMvc.perform(
            delete("/books/{id}", idToDelete)
        ).andExpect(status().isNoContent)
        verify(exactly = 1) { bookService.deleteBookById(idToDelete.toLong()) }
    }

}