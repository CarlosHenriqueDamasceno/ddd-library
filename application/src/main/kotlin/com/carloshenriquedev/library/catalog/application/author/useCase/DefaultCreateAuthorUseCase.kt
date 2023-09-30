package com.carloshenriquedev.library.catalog.application.author.useCase

import com.carloshenriquedev.library.catalog.application.author.AuthorRepository
import com.carloshenriquedev.library.catalog.domain.author.Author

internal class DefaultCreateAuthorUseCase(private val repository: AuthorRepository) : CreateAuthorUseCase {
    override fun execute(command: CreateAuthorCommand): AuthorOutput {
        val author = Author.buildNewAuthor(command.name)
        return AuthorOutput.from(repository.create(author))
    }
}