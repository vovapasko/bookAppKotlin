package com.mycompany.server

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.Table

@Entity
@Table(name = "books")
data class Book(
    @Id @GeneratedValue val id: Long,
    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val author: String,

    @Column(nullable = false)
    val read: Boolean
)