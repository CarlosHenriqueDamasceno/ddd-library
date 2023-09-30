package com.carloshenriquedev.library.catalog.domain.author

import com.carloshenriquedev.library.catalog.domain.AggregateRoot
import com.carloshenriquedev.library.catalog.domain.Identifier
import com.carloshenriquedev.library.catalog.domain.nowInMiliSeconds
import java.time.Instant
import java.util.*

class AuthorId(id: String) : Identifier(id)

class Author private constructor(
    id: AuthorId,
    var name: String,
    val createdAt: Instant,
    var updatedAt: Instant,
    var deletedAt: Instant?
) : AggregateRoot<AuthorId>(id) {


    companion object Builder {
        fun buildNewAuthor(name: String): Author {
            val now = nowInMiliSeconds()
            val id = AuthorId(UUID.randomUUID().toString())
            return Author(id, name, now, now, null)
        }
    }

}