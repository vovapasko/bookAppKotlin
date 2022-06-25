package com.mycompany.server

import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, String>