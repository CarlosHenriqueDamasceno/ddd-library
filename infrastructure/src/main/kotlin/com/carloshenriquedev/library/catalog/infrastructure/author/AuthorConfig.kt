package com.carloshenriquedev.library.catalog.infrastructure.author

import com.carloshenriquedev.library.catalog.application.author.port.AuthorGateway
import com.carloshenriquedev.library.catalog.application.author.port.AuthorUseCaseFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthorConfig(authorGateway: AuthorGateway) {

    val authorUseCaseFactory = AuthorUseCaseFactory(authorGateway)

    @Bean
    fun createAuthorUseCase() = authorUseCaseFactory.buildCreateAuthorUseCase()

}