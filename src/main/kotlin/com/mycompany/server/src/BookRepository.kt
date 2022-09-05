package com.mycompany.server.src

import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Long>