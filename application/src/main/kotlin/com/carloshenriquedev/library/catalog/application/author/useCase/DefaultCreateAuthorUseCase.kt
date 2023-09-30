package com.carloshenriquedev.library.catalog.application.author.useCase

import com.carloshenriquedev.library.catalog.application.author.AuthorRepository
import com.carloshenriquedev.library.catalog.domain.author.Author
import com.carloshenriquedev.library.catalog.domain.common.Either
import com.carloshenriquedev.library.catalog.domain.common.ValidationHandler

internal class DefaultCreateAuthorUseCase(private val repository: AuthorRepository) : CreateAuthorUseCase {
    override fun execute(command: CreateAuthorCommand): Either<AuthorOutput, ValidationHandler> {
        val author = Author.buildNewAuthor(command.name)
        return Either.Left(AuthorOutput.from(repository.create(author)))
    }
}