package com.carloshenriquedev.library.catalog.domain.author

import com.carloshenriquedev.library.catalog.domain.common.Error
import com.carloshenriquedev.library.catalog.domain.common.ValidationHandler
import com.carloshenriquedev.library.catalog.domain.common.Validator

class AuthorValidationHandler : ValidationHandler() {
    fun handleEmptyName() = handle(Error("Author's name can not be empty."))
}

internal class AuthorValidator(
    private val author: Author,
    handler: AuthorValidationHandler
) : Validator(handler) {

    override fun validate() {
        if (hasEmptyName())
            authorValidationHandler().handleEmptyName()
    }

    private fun authorValidationHandler() = handler as AuthorValidationHandler

    private fun hasEmptyName() = author.name.trim().isEmpty()
}