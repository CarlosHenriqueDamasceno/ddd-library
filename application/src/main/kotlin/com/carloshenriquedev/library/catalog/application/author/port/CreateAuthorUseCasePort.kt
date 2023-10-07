package com.carloshenriquedev.library.catalog.application.author.port

import com.carloshenriquedev.library.catalog.application.common.Either
import com.carloshenriquedev.library.catalog.application.common.UseCase
import com.carloshenriquedev.library.catalog.domain.author.Author
import com.carloshenriquedev.library.catalog.domain.common.ValidationHandler
import java.time.Instant

interface CreateAuthorUseCase : UseCase<CreateAuthorCommand, Either<AuthorOutput, ValidationHandler>> {
    override fun execute(command: CreateAuthorCommand): Either<AuthorOutput, ValidationHandler>
}

data class CreateAuthorCommand(val name: String)

data class AuthorOutput(
    val id: String,
    val name: String,
    val createdAt: Instant,
    val deletedAt: Instant?
) {
    companion object {
        fun from(author: Author) = AuthorOutput(
            author.id.value,
            author.name,
            author.createdAt,
            author.deletedAt
        )
    }
}