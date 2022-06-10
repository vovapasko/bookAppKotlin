package com.mycompany.server

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

val mapper = ObjectMapper()


@RestController
@RequestMapping("/books", produces = ["application/json"])
class BookController(val service: BookService) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/id")
    @ResponseBody
    fun getBookById(@PathVariable id: String): ResponseEntity<String> {
        val book = service.getById(id)
        if (book.isEmpty) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such a book with id $id")
        }
        val serializedBook = serializeBook(book.get())
        return ResponseEntity.ok(serializedBook)
    }


    @PostMapping
    fun createBook(@RequestBody book: Book): ResponseEntity<String> {
        val newBook = service.createBook(book)
        println(newBook)
        return ResponseEntity.ok().body(serializeBook(newBook))
    }

    @DeleteMapping("/id")
    fun deleteBook(@PathVariable id: String): ResponseEntity<String> {
        val bookDeleted = service.deleteBookById(id)
        if (bookDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such a book with id $id")
        }
        return ResponseEntity.ok("Deleted book")
    }


    private fun serializeBook(book: Book): String {
        return mapper.writeValueAsString(book)
    }
}