package com.carloshenriquedev.library.catalog.application.author.useCase

import com.carloshenriquedev.library.catalog.application.author.port.AuthorGateway
import com.carloshenriquedev.library.catalog.application.author.port.AuthorOutput
import com.carloshenriquedev.library.catalog.application.author.port.CreateAuthorCommand
import com.carloshenriquedev.library.catalog.application.author.port.CreateAuthorUseCase
import com.carloshenriquedev.library.catalog.application.common.Either
import com.carloshenriquedev.library.catalog.domain.author.Author
import com.carloshenriquedev.library.catalog.domain.common.DomainException
import com.carloshenriquedev.library.catalog.domain.common.ValidationHandler

internal class DefaultCreateAuthorUseCase(private val repository: AuthorGateway) : CreateAuthorUseCase {
    override fun execute(command: CreateAuthorCommand): Either<AuthorOutput, ValidationHandler> {
        val author = try {
            Author.create(command.name)
        } catch (exception: DomainException) {
            return Either.Right(exception.handler)
        }
        return Either.Left(AuthorOutput.from(repository.create(author)))
    }
}