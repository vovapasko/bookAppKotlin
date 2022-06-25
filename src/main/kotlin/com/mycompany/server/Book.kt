package com.mycompany.server

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue

@Entity
data class Book(@Id @GeneratedValue val id: Long, val title: String, val author: String, val read: Boolean)