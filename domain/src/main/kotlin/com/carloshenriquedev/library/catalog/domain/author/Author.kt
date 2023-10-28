package com.carloshenriquedev.library.catalog.domain.author

import com.carloshenriquedev.library.catalog.domain.common.AggregateRoot
import com.carloshenriquedev.library.catalog.domain.common.DomainException
import com.carloshenriquedev.library.catalog.domain.common.Identifier
import com.carloshenriquedev.library.catalog.domain.common.nowInMiliSeconds
import java.time.Instant
import java.util.*

class AuthorId(id: String) : Identifier(id)

class Author private constructor(
    id: AuthorId,
    var name: String,
    val createdAt: Instant,
    var updatedAt: Instant,
    var deletedAt: Instant?,
) : AggregateRoot<AuthorId>(id) {

    init {
        validate()
    }

    companion object Builder {
        fun create(name: String): Author {
            val now = nowInMiliSeconds()
            val id = AuthorId(UUID.randomUUID().toString())
            return Author(id, name, now, now, null)
        }

        fun restore(
            id: String,
            name: String,
            createdAt: Instant,
            updatedAt: Instant,
            deletedAt: Instant?,
        ) = Author(
            AuthorId(id),
            name,
            createdAt,
            updatedAt,
            deletedAt
        )
    }

    override fun validate() {
        val authorValidationHandler = AuthorValidationHandler()
        val validator = AuthorValidator(this, authorValidationHandler)
        validator.validate()
        if (authorValidationHandler.size > 0)
            throw DomainException("Failed to create a Aggregate Author", authorValidationHandler)
    }
}