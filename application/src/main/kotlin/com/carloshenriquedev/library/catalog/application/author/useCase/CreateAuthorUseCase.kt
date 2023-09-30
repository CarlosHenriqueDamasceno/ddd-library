package com.carloshenriquedev.library.catalog.application.author.useCase

import com.carloshenriquedev.library.catalog.application.UseCase
import com.carloshenriquedev.library.catalog.domain.author.Author
import java.time.Instant

interface CreateAuthorUseCase : UseCase<CreateAuthorCommand, AuthorOutput> {
    override fun execute(command: CreateAuthorCommand): AuthorOutput
}

data class CreateAuthorCommand(val name: String)

data class AuthorOutput(
    val id: String,
    val name: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?
) {
    companion object {
        fun from(author: Author) = AuthorOutput(
            author.id.value,
            author.name,
            author.createdAt,
            author.updatedAt,
            author.deletedAt
        )
    }
}