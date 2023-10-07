package com.carloshenriquedev.library.catalog.infrastructure.author.adapter

import com.carloshenriquedev.library.catalog.application.author.port.AuthorGateway
import com.carloshenriquedev.library.catalog.domain.author.Author
import org.springframework.stereotype.Component

@Component
class AuthorJPAGateway(private val repository: AuthorJpaRepository) : AuthorGateway {
    override fun create(author: Author) = repository.save(AuthorJPAEntity.from(author)).toAggragate()
}