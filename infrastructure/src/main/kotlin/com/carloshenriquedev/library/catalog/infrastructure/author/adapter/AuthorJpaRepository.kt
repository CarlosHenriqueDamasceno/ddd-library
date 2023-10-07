package com.carloshenriquedev.library.catalog.infrastructure.author.adapter

import org.springframework.data.jpa.repository.JpaRepository

interface AuthorJpaRepository : JpaRepository<AuthorJPAEntity, String>