package com.mycompany.server

import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class BookService(val repository: BookRepository) {
    fun getAll() = mapOf("books" to repository.findAll())

    fun getById(id: Long) = repository.findById(id)
    fun createBook(book: Book) = repository.save(book)

    fun deleteBookById(id: Long) = if (repository.existsById(id)) {
        repository.deleteById(id)
        true
    } else
        false
}
