package com.carloshenriquedev.library.catalog.application.author

import com.carloshenriquedev.library.catalog.domain.author.Author

interface AuthorRepository {
    fun create(author: Author): Author
}