package com.mycompany.server

import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
@Transactional
class BookService(val repository: BookRepository) {
    fun getAll() = repository.findAll()

    fun getById(id: Long) = repository.findById(id)
    fun createBook(book: Book) = repository.save(book)

    fun deleteBookById(id: Long): Boolean {
        val bookExists = repository.existsById(id)
        if (bookExists) {
            repository.deleteById(id)
        }
        return bookExists
    }


}