package com.mycompany.server

import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
@Transactional
class BookService(val repository: BookRepository) {
    fun getAll() = repository.findAll()

    fun getById(id: String) = repository.findById(id)
    fun createBook(book: Book) = repository.save(book)

    fun deleteBookById(id: String): Boolean {
        val bookExists = repository.existsById(id)
        if (bookExists) {
            repository.deleteById(id)
        }
        return bookExists
    }


}