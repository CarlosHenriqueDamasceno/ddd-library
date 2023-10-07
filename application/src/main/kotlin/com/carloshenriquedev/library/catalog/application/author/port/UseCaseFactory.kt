package com.carloshenriquedev.library.catalog.application.author.port

import com.carloshenriquedev.library.catalog.application.author.useCase.DefaultCreateAuthorUseCase

class AuthorUseCaseFactory(private val gateway: AuthorGateway) {
    fun buildCreateAuthorUseCase(): CreateAuthorUseCase = DefaultCreateAuthorUseCase(gateway)
}