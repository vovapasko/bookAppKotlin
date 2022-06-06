package com.mycompany.server

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/books")
class BookController {
    @GetMapping
    fun getAll() = "not implemented"
}